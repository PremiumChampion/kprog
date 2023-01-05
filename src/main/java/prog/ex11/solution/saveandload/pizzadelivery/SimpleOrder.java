package prog.ex11.solution.saveandload.pizzadelivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;

/**
 * Simple and straight-forward implementation of the Order interface.
 */
public class SimpleOrder implements Order, Serializable {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SimpleOrder.class);
  private static int nextOrderId = 0;
  private final int orderId;
  private final List<Pizza> pizzaList;
  private OrderState state;
  private int price;

  /**
   * creates a new simple order.
   */
  public SimpleOrder() {
    state = OrderState.PizzaDeliveryService;
    this.orderId = nextOrderId++;
    this.pizzaList = new ArrayList<>();
  }

  /**
   * creates a new simple order without a pizza delivery service.
   *
   * @param orderId the orderid.
   */
  public SimpleOrder(int orderId) {
    state = OrderState.Standalone;
    this.orderId = orderId;
    this.pizzaList = new ArrayList<>();
  }

  @Override
  public int getOrderId() {
    return this.orderId;
  }

  @Override
  public List<Pizza> getPizzaList() {
    return Collections.unmodifiableList(this.pizzaList);
  }

  /**
   * setter.
   *
   * @param price new price.
   */
  public void setPrice(int price) {
    if (state != OrderState.Standalone) {
      throw new IllegalStateException("Expected state to be Standalone but was: " + state);
    }
    this.price = price;
  }

  /**
   * add pizza.
   *
   * @param pizzaToAdd pizza to add.
   */
  public void addPizza(Pizza pizzaToAdd) {
    this.pizzaList.add(pizzaToAdd);
  }

  /**
   * remove pizza.
   *
   * @param pizzaToRemove pizza.
   */
  public void removePizza(Pizza pizzaToRemove) {
    this.pizzaList.remove(pizzaToRemove);
  }

  public void removePizza(int pizzaToRemove) {
    this.pizzaList.remove(pizzaToRemove);
  }

  @Override
  public int getValue() {
    return this.pizzaList.stream().mapToInt(Pizza::getPrice).sum();
  }

  @Override
  public String toString() {
    return "SimpleOrder{" + "orderId=" + getOrderId() + ", pizzaList=" + getOrderId() + ", price="
        + getValue() + '}';
  }

  enum OrderState {
    PizzaDeliveryService, Standalone
  }


}
