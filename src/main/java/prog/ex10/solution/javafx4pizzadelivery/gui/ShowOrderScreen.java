package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.io.IOException;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;

/**
 * Screen to show the list of pizzas of an order of the PizzaDeliveryService. It is also possible to
 * add, change and remove pizzas.
 */
public class ShowOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreen.class);

  public static final String SCREEN_NAME = "ShowOrderScreen";
  private final PizzaDeliveryScreenController screenController;
  private final SingletonAttributeStore store;
  private final PizzaDeliveryService service;
  private final IntegerProperty orderId;
  private final IntegerProperty pizzaId;
  private final IntegerProperty price;
  private final ShowOrderScreenController controller;
  private ObservableList<Pizza> pizzas;

  /**
   * show me stuff.
   *
   * @param screenController some screen controller.
   */
  public ShowOrderScreen(PizzaDeliveryScreenController screenController) {
    this.screenController = screenController;
    this.store = SingletonAttributeStore.getInstance();

    this.service = (PizzaDeliveryService) store.getAttribute(
        SingletonAttributeStore.PIZZA_DELIVERY_SERVICE);
    this.orderId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID);
    this.pizzaId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID);
    this.price = new SimpleIntegerProperty();

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("ShowOrderScreen.fxml"),
          "ShowOrderScreen.fxml resource not found."));

      this.getChildren().setAll((Node) loader.load());
      this.controller = loader.getController();

      initController();
    } catch (IOException | NullPointerException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  /**
   * initialises the controller and necessary events.
   */
  void initController() {
    this.controller.setOnAddEventHandler(this::onAddPizza);
    this.controller.setOnCancelOrderEventHandler(this::onCancelOrder);
    this.controller.setOnFinishOrderEventHandler(this::onSubmitOrder);
    this.controller.setOnEditEventHandler(this::onEditPizza);
    this.controller.setOnRemoveEventHandler(this::removePizza);
    this.controller.getOrderIdLabelTextProperty().bind(this.orderId.asString("Order-Id: %d"));
    this.controller.getOrderPriceLabelTextProperty().bind(this.price.asString("Preis: %d ct"));
    this.pizzas = this.controller.getPizzaList();
    this.updatePizzaList();
  }

  /**
   * remove pizza handler.
   *
   * @param pizza pizza to remove.
   */
  private void removePizza(Pizza pizza) {
    service.removePizza(this.orderId.getValue(), pizza.getPizzaId());
    updatePizzaList();
  }

  /**
   * update pizza list.
   */
  private void updatePizzaList() {
    this.pizzas.clear();
    this.pizzas.addAll(service.getOrder(this.orderId.getValue()).getPizzaList());
    this.price.setValue(service.getOrder(this.orderId.getValue()).getValue());
  }

  /**
   * edit pizza handler.
   *
   * @param pizza to edit.
   */
  private void onEditPizza(Pizza pizza) {
    this.pizzaId.setValue(pizza.getPizzaId());
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
  private void onSubmitOrder() {
    this.orderId.setValue(-1);
    try {
      this.screenController.switchTo(ShowOrderScreen.SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  /**
   * cancel order handler.
   */
  private void onCancelOrder() {
    this.orderId.setValue(-1);
    try {
      this.screenController.switchTo(ShowOrderScreen.SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }

  /**
   * add pizza handler.
   */
  void onAddPizza() {
    int newPizzaId = this.service.addPizza(this.orderId.getValue(),
        controller.getSelectedPizzaSize());
    this.pizzaId.setValue(newPizzaId);

    try {
      screenController.switchTo(ShowOrderScreen.SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).showAndWait();
      throw new RuntimeException(e);
    }
  }
}
