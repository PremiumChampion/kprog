package prog.ex09.solution.editpizzascreen.pizzadelivery;

import java.util.Map;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Order;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the PizzaDeliveryService interface.
 */
public class SimplePizzaDeliveryService implements PizzaDeliveryService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);

  public SimplePizzaDeliveryService() {
  }

  @Override
  public int createOrder() {
    return 0;
  }

  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public void removePizza(final int orderId, final int pizzaId) throws IllegalArgumentException {

  }

  @Override
  public void addTopping(final int pizzaId, final Topping topping)
          throws IllegalArgumentException, TooManyToppingsException {

  }

  @Override
  public void removeTopping(final int pizzaId, final Topping topping)
          throws IllegalArgumentException {

  }

  @Override
  public Order getOrder(final int orderId) {
    return null;
  }

  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() {
    return null;
  }

  @Override
  public Map<Topping, Integer> getToppingsPriceList() {
    return null;
  }
}
