package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.io.IOException;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;

/**
 * Screen to create an order in the PizzaDeliveryService.
 */
public class CreateOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";
  private final PizzaDeliveryScreenController screenController;
  private final SingletonAttributeStore store;
  private final PizzaDeliveryService service;
  private final IntegerProperty orderId;
  private final IntegerProperty pizzaId;
  private final CreateOrderScreenController controller;

  /**
   * Create Order Screen.
   *
   * @param screenController screen controller
   */
  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {

    this.screenController = screenController;
    this.store = SingletonAttributeStore.getInstance();

    this.service = (PizzaDeliveryService) store.getAttribute(
        SingletonAttributeStore.PIZZA_DELIVERY_SERVICE);
    this.orderId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID);
    this.pizzaId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID);

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("CreateOrderScreen.fxml"),
          "CreateOrderScreen.fxml resource not found."));

      this.getChildren().setAll((Node) loader.load());
      this.controller = loader.getController();
      this.controller.setOnCreateOrder(this::onCreateOrder);

    } catch (IOException | NullPointerException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }

  }

  /**
   * creates a order and navigates in the view order screen.
   */
  private void onCreateOrder() {
    orderId.setValue(service.createOrder());

    try {
      screenController.switchTo(CreateOrderScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).show();
    }
  }
}
