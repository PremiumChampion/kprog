package prog.ex06.exercise.pizzadelivery;

import java.util.Map;

/**
 * This service allows to order a set of pizzas with different sizes and different toppings.
 */
public interface PizzaDeliveryService {
  static int MAX_TOPPINGS_PER_PIZZA = 6;

  /**
   * Creates internally an Order object.
   *
   * @return id of the newly created Order object
   */
  int createOrder();

  /**
   * Adds a pizza of the given size to the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @param size    size of the pizza
   * @return id of the pizza
   * @throws IllegalArgumentException if the orderId is not a valid order id.
   */
  int addPizza(int orderId, PizzaSize size) throws IllegalArgumentException;

  /**
   * Removes a pizza from the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @param pizzaId id of the pizza
   * @throws IllegalArgumentException if either the orderId is not a valid order or the pizzaId
   *                                  is not a valid pizza
   */
  void removePizza(int orderId, int pizzaId) throws IllegalArgumentException;

  /**
   * Adds a Topping to the pizza referenced by the pizzaId.
   *
   * @param pizzaId id of the pizza
   * @param topping Topping to be added
   * @throws IllegalArgumentException if the pizzaId is not a valid pizza
   * @throws TooManyToppingsException if there are already MAX_TOPPINGS_PER_PIZZA Toppings on the
   *                                  pizza
   */
  void addTopping(int pizzaId, Topping topping) throws IllegalArgumentException,
          TooManyToppingsException;

  /**
   * Removes a topping from the pizza referenced by the pizzaId.
   *
   * @param pizzaId id of the pizza
   * @param topping Topping to be removed
   * @throws IllegalArgumentException if either the pizzaId is not a valid pizza or the Topping
   *                                  is not configured for the referenced pizza
   */
  void removeTopping(int pizzaId, Topping topping) throws IllegalArgumentException;

  /**
   * Returns the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @return Order referenced by the id
   * @throws IllegalArgumentException if the orderId is not a valid one
   */
  Order getOrder(int orderId) throws IllegalArgumentException;

  /**
   * Returns the price information for different Pizza sizes.
   *
   * @return Map with price per PizzaSize
   */
  Map<PizzaSize, Integer> getPizzaSizePriceList();

  /**
   * Returns the price information for Toppings.
   *
   * @return Map with price per Topping
   */
  Map<Topping, Integer> getToppingsPriceList();
}
