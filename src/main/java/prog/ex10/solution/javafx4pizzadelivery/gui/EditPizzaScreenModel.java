package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;

/**
 * class EditPizzaScreenModel.
 */
public class EditPizzaScreenModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreenModel.class);

  private final ObservableList<Topping> selectedToppings = FXCollections.observableList(
      new ArrayList<>());
  private final IntegerProperty orderId = new SimpleIntegerProperty();
  private final IntegerProperty pizzaId = new SimpleIntegerProperty();
  private final ObjectProperty<Pizza> pizza = new SimpleObjectProperty<>();
  private final IntegerProperty price = new SimpleIntegerProperty();
  private final ObjectProperty<PizzaSize> size = new SimpleObjectProperty<>();

  //region getter/setter
  public ObservableList<Topping> getSelectedToppings() {
    return selectedToppings;
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

  public Pizza getPizza() {
    return pizza.get();
  }

  public ObjectProperty<Pizza> pizzaProperty() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza.set(pizza);
  }

  public int getPrice() {
    return price.get();
  }

  public IntegerProperty priceProperty() {
    return price;
  }

  public void setPrice(int price) {
    this.price.set(price);
  }

  public PizzaSize getSize() {
    return size.get();
  }

  public ObjectProperty<PizzaSize> sizeProperty() {
    return size;
  }

  public void setSize(PizzaSize size) {
    this.size.set(size);
  }
  //endregion
}
