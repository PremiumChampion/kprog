package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;

/**
 * class ShowOrderScreenModel.
 */
public class ShowOrderScreenModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreenModel.class);
  private ObservableList<Pizza> pizzasInOrderList = FXCollections.observableList(new ArrayList<>());
  private final IntegerProperty orderId = new SimpleIntegerProperty();
  private final IntegerProperty pizzaId = new SimpleIntegerProperty();
  private final IntegerProperty preis = new SimpleIntegerProperty();
  private final ObjectProperty<PizzaSize> pizzaSize = new SimpleObjectProperty<>();
  private final ObjectProperty<PizzaDeliveryService> pizzaDeliveryService =
      new SimpleObjectProperty<>();

  //region getter/setter
  public ObservableList<Pizza> getPizzasInOrderList() {
    return pizzasInOrderList;
  }

  public void setPizzasInOrderList(
      ObservableList<Pizza> pizzasInOrderList) {
    this.pizzasInOrderList = pizzasInOrderList;
  }

  public int getOrderId() {
    return orderId.get();
  }

  public IntegerProperty orderIdProperty() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId.set(orderId);
  }

  public int getPizzaId() {
    return pizzaId.get();
  }

  public IntegerProperty pizzaIdProperty() {
    return pizzaId;
  }

  public void setPizzaId(int pizzaId) {
    this.pizzaId.set(pizzaId);
  }

  public int getPreis() {
    return preis.get();
  }

  public IntegerProperty preisProperty() {
    return preis;
  }

  public void setPreis(int preis) {
    this.preis.set(preis);
  }

  public PizzaSize getPizzaSize() {
    return pizzaSize.get();
  }

  public ObjectProperty<PizzaSize> pizzaSizeProperty() {
    return pizzaSize;
  }

  public void setPizzaSize(PizzaSize pizzaSize) {
    this.pizzaSize.set(pizzaSize);
  }

  public PizzaDeliveryService getPizzaDeliveryService() {
    return pizzaDeliveryService.get();
  }

  public ObjectProperty<PizzaDeliveryService> pizzaDeliveryServiceProperty() {
    return pizzaDeliveryService;
  }

  public void setPizzaDeliveryService(PizzaDeliveryService pizzaDeliveryService) {
    this.pizzaDeliveryService.set(pizzaDeliveryService);
  }
  //endregion
}
