package prog.ex10.solution.javafx4pizzadelivery.gui;

import static prog.ex10.solution.javafx4pizzadelivery.gui.SingletonAttributeStore.ORDER_ID;
import static prog.ex10.solution.javafx4pizzadelivery.gui.SingletonAttributeStore.PIZZA_DELIVERY_SERVICE;

import examples.javafx.modal.ExceptionAlert;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;

/**
 * Screen to create an order in the PizzaDeliveryService.
 */
public class CreateOrderScreen extends VBox implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";
  private final PizzaDeliveryScreenController screenController;
  @FXML
  public Button createOrderButton;
  private final CreateOrderScreenModel model;

  /**
   * Create Order Screen.
   *
   * @param screenController screen controller
   */
  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {
    this.model = new CreateOrderScreenModel();
    this.screenController = screenController;

    SingletonAttributeStore store = SingletonAttributeStore.getInstance();

    model.setDeliveryService((PizzaDeliveryService) store.getAttribute(PIZZA_DELIVERY_SERVICE));
    model.orderIdProperty().bindBidirectional((IntegerProperty) store.getAttribute(ORDER_ID));

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("ex10/CreateOrderScreen.fxml"),
          "CreateOrderScreen.fxml resource not found."));

      loader.setController(this);
      this.getChildren().clear();
      loader.load();
      this.getChildren().add(loader.getRoot());

    } catch (IOException | NullPointerException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  /**
   * creates a order and navigates in the view order screen.
   */
  private void onCreateOrder(ActionEvent e) {
    int orderId = model.getDeliveryService().createOrder();
    model.setOrderId(orderId);

    try {
      screenController.switchTo(CreateOrderScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException ex) {
      new ExceptionAlert(ex).show();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.createOrderButton.setOnAction(this::onCreateOrder);
  }
}
