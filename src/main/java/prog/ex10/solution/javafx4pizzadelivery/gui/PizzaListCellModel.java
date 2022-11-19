package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;

/**
 * class PizzaListCellModel.
 */
public class PizzaListCellModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaListCellModel.class);

  private final ObjectProperty<PizzaSize> size = new SimpleObjectProperty<>();
  private final ObjectProperty<Pizza> pizza = new SimpleObjectProperty<>();
  private final IntegerProperty toppingCount = new SimpleIntegerProperty();

  //region getter/setter
  public PizzaSize getSize() {
    return size.get();
  }

  public ObjectProperty<PizzaSize> sizeProperty() {
    return size;
  }

  public void setSize(PizzaSize size) {
    this.size.set(size);
  }

  public int getToppingCount() {
    return toppingCount.get();
  }

  public IntegerProperty toppingCountProperty() {
    return toppingCount;
  }

  public void setToppingCount(int toppingCount) {
    this.toppingCount.set(toppingCount);
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
//endregion
}
