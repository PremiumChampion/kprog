package prog.ex11.solution.saveandload.pizzadelivery;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaDeliveryService;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.TooManyToppingsException;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the PizzaDeliveryService interface.
 */
public class SimplePizzaDeliveryService implements PizzaDeliveryService {

  public static final Map<PizzaSize, Integer> sizePriceList;
  public static final Map<Topping, Integer> toppingPriceList;

  static {
    Map<PizzaSize, Integer> tmpSizePriceList = new HashMap<>();
    tmpSizePriceList.put(PizzaSize.SMALL, 500);
    tmpSizePriceList.put(PizzaSize.MEDIUM, 700);
    tmpSizePriceList.put(PizzaSize.LARGE, 900);
    tmpSizePriceList.put(PizzaSize.EXTRA_LARGE, 1100);

    Map<Topping, Integer> tmpToppingPriceList = new HashMap<>();
    tmpToppingPriceList.put(Topping.TOMATO, 30);
    tmpToppingPriceList.put(Topping.CHEESE, 60);
    tmpToppingPriceList.put(Topping.SALAMI, 50);
    tmpToppingPriceList.put(Topping.HAM, 70);
    tmpToppingPriceList.put(Topping.PINEAPPLE, 90);
    tmpToppingPriceList.put(Topping.VEGETABLES, 20);
    tmpToppingPriceList.put(Topping.SEAFOOD, 150);

    sizePriceList = Collections.unmodifiableMap(tmpSizePriceList);
    toppingPriceList = Collections.unmodifiableMap(tmpToppingPriceList);
  }

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);

  private final Map<Integer, SimpleOrder> orders;
  private final Map<Integer, SimplePizza> pizza;

  public SimplePizzaDeliveryService() {
    this.orders = new HashMap<>();
    this.pizza = new HashMap<>();
  }

  @Override
  public int createOrder() {
    SimpleOrder newOrder = new SimpleOrder();
    int newOrderId = newOrder.getOrderId();

    this.orders.put(newOrderId, newOrder);
    return newOrderId;
  }

  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {
    if (!this.orders.containsKey(orderId)) {
      throw new IllegalArgumentException(String.format("invalid order id (%s)", orderId));
    }

    SimpleOrder order = this.orders.get(orderId);
    SimplePizza newPizza = new SimplePizza(this, size);

    int newPizzaId = newPizza.getPizzaId();
    this.pizza.put(newPizzaId, newPizza);
    order.addPizza(newPizza);

    return newPizzaId;
  }

  @Override
  public void removePizza(final int orderId, final int pizzaId) throws IllegalArgumentException {
    if (!this.orders.containsKey(orderId)) {
      throw new IllegalArgumentException(String.format("invalid order id (%s)", orderId));
    }

    SimpleOrder order = this.orders.get(orderId);

    List<Pizza> pizzaList = order.getPizzaList();

    Pizza pizzaToRemove = pizzaList.stream().filter(p -> p.getPizzaId() == pizzaId).findFirst()
        .orElse(null);

    if (pizzaToRemove == null) {
      throw new IllegalArgumentException(String.format("invalid pizza id (%s)", pizzaId));
    }
    pizza.remove(pizzaId);
    order.removePizza(pizzaToRemove);
  }

  @Override
  public void addTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException, TooManyToppingsException {
    if (!this.pizza.containsKey(pizzaId)) {
      throw new IllegalArgumentException(String.format("invalid pizza id (%s)", pizzaId));
    }

    SimplePizza pizza = this.pizza.get(pizzaId);

    int toppingCount = pizza.getToppingCount();

    if (toppingCount == MAX_TOPPINGS_PER_PIZZA) {
      throw new TooManyToppingsException(
          String.format("pizza with id %s already has %s toppings when trying to add %s. (max: %s)",
              pizzaId,
              toppingCount,
              topping,
              MAX_TOPPINGS_PER_PIZZA
          )
      );
    }

    pizza.addTopping(topping);
  }

  @Override
  public void removeTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException {
    if (!this.pizza.containsKey(pizzaId)) {
      throw new IllegalArgumentException(String.format("invalid pizza id (%s)", pizzaId));
    }

    SimplePizza pizza = this.pizza.get(pizzaId);
    List<Topping> toppings = pizza.getToppings();
    if (!toppings.contains(topping)) {
      throw new IllegalArgumentException(
          String.format("pizza (%s) does not contain topping %s", pizzaId, topping)
      );
    }

    pizza.removeTopping(topping);
  }

  @Override
  public Order getOrder(final int orderId) {
    if (!this.orders.containsKey(orderId)) {
      throw new IllegalArgumentException(String.format("invalid order id (%s)", orderId));
    }
    return this.orders.get(orderId);
  }

  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() {
    return Collections.unmodifiableMap(sizePriceList);
  }

  @Override
  public Map<Topping, Integer> getToppingsPriceList() {
    return Collections.unmodifiableMap(toppingPriceList);
  }
}
