package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Order;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;

/**
 * Screen to show the list of pizzas of an order of the PizzaDeliveryService. It is also possible to
 * add, change and remove pizzas.
 */
public class ShowOrderScreen extends VBox implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreen.class);

  public static final String SCREEN_NAME = "ShowOrderScreen";
  private final PizzaDeliveryScreenController screenController;
  private final SingletonAttributeStore store;
  private final ShowOrderScreenModel model;
  @FXML
  public Label orderIdLabel;
  @FXML
  public Label orderPriceLabel;
  @FXML
  public ChoiceBox<PizzaSize> pizzaSizeChoiceBox;
  @FXML
  public Button addPizzaButton;
  @FXML
  public ListView<Pizza> pizzaInOrderList;
  @FXML
  public Button cancelOrderButton;
  @FXML
  public Button onSubmitOrderButton;


  /**
   * show me stuff.
   *
   * @param screenController some screen controller.
   */
  public ShowOrderScreen(PizzaDeliveryScreenController screenController) {
    this.screenController = screenController;
    this.store = SingletonAttributeStore.getInstance();
    this.model = new ShowOrderScreenModel();

    PizzaDeliveryService service = (PizzaDeliveryService) store.getAttribute(
        SingletonAttributeStore.PIZZA_DELIVERY_SERVICE);
    model.setPizzaDeliveryService(service);

    IntegerProperty order = (IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID);
    model.orderIdProperty().bindBidirectional(order);
    model.orderIdProperty().addListener(this::orderIdChanged);

    IntegerProperty pizza = (IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID);
    model.pizzaIdProperty().bindBidirectional(pizza);
    model.pizzaIdProperty().addListener(this::pizzaIdChanged);

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("ShowOrderScreen.fxml"),
          "ShowOrderScreen.fxml resource not found."));
      loader.setController(this);
      this.getChildren().clear();
      this.getChildren().add(loader.load());

      updatePizzaList();
    } catch (IOException | NullPointerException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  private void pizzaIdChanged(Observable observable) {
    updatePizzaList();
  }

  private void orderIdChanged(Observable observable) {
    updatePizzaList();
  }

  /**
   * remove pizza handler.
   *
   * @param pizza pizza to remove.
   */
  private void onRemovePizza(Pizza pizza) {
    int orderId = model.getOrderId();
    model.getPizzaDeliveryService().removePizza(orderId, pizza.getPizzaId());
    updatePizzaList();
  }

  /**
   * update pizza list.
   */
  private void updatePizzaList() {
    int orderId = model.getOrderId();
    if (orderId == -1) {
      return;
    }
    model.getPizzasInOrderList().clear();
    Order order = model.getPizzaDeliveryService().getOrder(orderId);
    model.getPizzasInOrderList()
        .addAll(order.getPizzaList());
    model.setPreis(order.getValue());
  }

  /**
   * edit pizza handler.
   *
   * @param pizza to edit.
   */
  private void onEditPizza(Pizza pizza) {
    ((IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID)).setValue(
        pizza.getPizzaId());
    try {
      screenController.switchTo(ShowOrderScreen.SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  /**
   * submit order handler.
   */
  private void onSubmitOrder(ActionEvent e) {
    ((IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID)).setValue(-1);
    try {
      this.screenController.switchTo(ShowOrderScreen.SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException ex) {
      new ExceptionAlert(ex).showAndWait();
      throw new RuntimeException(ex);
    }
  }

  /**
   * cancel order handler.
   */
  private void onCancelOrder(ActionEvent e) {
    ((IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID)).setValue(-1);
    try {
      this.screenController.switchTo(ShowOrderScreen.SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException ex) {
      new ExceptionAlert(ex).showAndWait();
      throw new RuntimeException(ex);
    }
  }

  /**
   * add pizza handler.
   */
  void onAddPizza(ActionEvent e) {
    int newPizzaId = model.getPizzaDeliveryService()
        .addPizza(model.getOrderId(), model.getPizzaSize());
    ((IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID)).setValue(newPizzaId);

    try {
      screenController.switchTo(ShowOrderScreen.SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
    } catch (UnknownTransitionException ex) {
      new ExceptionAlert(ex).showAndWait();
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    pizzaSizeChoiceBox.valueProperty().bindBidirectional(model.pizzaSizeProperty());
    pizzaSizeChoiceBox.getSelectionModel().selectFirst();

    orderIdLabel.textProperty().bind(model.orderIdProperty().asString("Order-Id: %d"));

    orderPriceLabel.textProperty().bind(model.preisProperty().asString("Preis: %d ct"));

    addPizzaButton.setOnAction(this::onAddPizza);

    cancelOrderButton.setOnAction(this::onCancelOrder);

    onSubmitOrderButton.setOnAction(this::onSubmitOrder);

    pizzaSizeChoiceBox.getItems().addAll(PizzaSize.values());
    pizzaSizeChoiceBox.getSelectionModel().selectFirst();

    pizzaInOrderList.setItems(model.getPizzasInOrderList());
    pizzaInOrderList.setCellFactory(view -> {
      PizzaListCell pizzaListCell = new PizzaListCell();
      pizzaListCell.setOnEditEventHandler(this::onEditPizza);
      pizzaListCell.setOnRemoveEventHandler(this::onRemovePizza);
      return pizzaListCell;
    });
  }
}
