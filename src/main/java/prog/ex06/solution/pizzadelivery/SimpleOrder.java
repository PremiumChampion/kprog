package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import prog.ex06.exercise.pizzadelivery.Order;
import prog.ex06.exercise.pizzadelivery.Pizza;

/**
 * Simple and straight-forward implementation of the Order interface.
 */
public class SimpleOrder implements Order {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);
  private static int nextOrderId = 0;
  private final int orderId;
  private final List<Pizza> pizzaList;

  public SimpleOrder() {
    this.orderId = nextOrderId++;
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
   * add pizza.
   *
   * @param pizzaToAdd pizza to add.
   */
  public void addPizza(Pizza pizzaToAdd) {
    this.pizzaList.add(pizzaToAdd);
  }

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
}
