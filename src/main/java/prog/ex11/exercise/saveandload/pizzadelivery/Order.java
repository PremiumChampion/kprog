package prog.ex11.exercise.saveandload.pizzadelivery;

import java.util.List;

/**
 * Represents an order at the PizzaDeliveryService.
 */
public interface Order {
  /**
   * Returns the id of the order.
   *
   * @return id of the order.
   */
  int getOrderId();

  /**
   * Returns a list of pizza objects the order contains.
   *
   * @return List of Pizza objects.
   */
  List<Pizza> getPizzaList();

  /**
   * Returns the cumulated price of the order.
   *
   * @return price of the order
   */
  int getValue();
}
