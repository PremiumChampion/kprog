package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaDeliveryService;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  private static int nextPizzaId = 0;
  private final PizzaSize size;
  private final int pizzaId;
  private List<Topping> toppings;
  private PizzaDeliveryService service;

  /**
   * creates a simple pizza.
   *
   * @param service the service creating the pizza.
   * @param size    the size of the pizza.
   */
  public SimplePizza(PizzaDeliveryService service, PizzaSize size) {
    this.pizzaId = nextPizzaId++;
    this.toppings = new ArrayList<>();
    this.size = size;
    this.service = service;
  }

  @Override
  public int getPizzaId() {
    return this.pizzaId;
  }

  @Override
  public List<Topping> getToppings() {
    return Collections.unmodifiableList(this.toppings);
  }

  public void addTopping(Topping toppingToAdd) {
    this.toppings.add(toppingToAdd);
  }

  public void removeTopping(Topping toppingToRemove) {
    this.toppings.remove(toppingToRemove);
  }

  public void removeTopping(int toppingToRemove) {
    this.toppings.remove(toppingToRemove);
  }

  public int getToppingCount() {
    return this.toppings.size();
  }

  @Override
  public PizzaSize getSize() {
    return this.size;
  }

  @Override
  public int getPrice() {
    Map<PizzaSize, Integer> sizePriceList = service.getPizzaSizePriceList();
    Map<Topping, Integer> toppingPriceList = service.getToppingsPriceList();

    int totalPizzaPrice = 0;

    Integer basePizzaPrice = sizePriceList.get(this.size);
    totalPizzaPrice += basePizzaPrice;

    for (Topping topping : toppings) {
      Integer toppingPrice = toppingPriceList.get(topping);
      totalPizzaPrice += toppingPrice;
    }

    return totalPizzaPrice;
  }
}
