package prog.ex11.solution.saveandload.pizzadelivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaDeliveryService;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza, Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  private static int nextPizzaId = 0;
  private PizzaSize size;
  private final int pizzaId;
  private List<Topping> toppings;
  private PizzaDeliveryService service;
  private int pizzaPrice;
  private PizzaState state;

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
    state = PizzaState.PizzaDeliveryService;
  }

  public SimplePizza(int pizzaId) {
    this.pizzaId = pizzaId;
    this.toppings = new ArrayList<>();
    state = PizzaState.Standalone;
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

  public void setSize(PizzaSize size) {
    if (state != PizzaState.Standalone) {
      throw new IllegalStateException("Expected state to be Standalone but was: " + state);
    }
    this.size = size;
  }

  public void setPrice(int price) {
    if (state != PizzaState.Standalone) {
      throw new IllegalStateException("Expected state to be Standalone but was: " + state);
    }
    this.pizzaPrice = price;
  }

  @Override
  public int getPrice() {
    if (state == PizzaState.PizzaDeliveryService) {
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
    if (state == PizzaState.Standalone) {
      return pizzaPrice;
    }

    throw new IllegalStateException(
        "Expected state to be PizzaDeliveryService or Standalone but was: " + state);
  }

  @Override
  public String toString() {
    return "SimplePizza{" +
        "size=" + getSize() +
        ", pizzaId=" + getPizzaId() +
        ", toppings=" + getToppings() +
        ", pizzaPrice=" + getPrice() +
        '}';
  }

  enum PizzaState {
    PizzaDeliveryService,
    Standalone
  }
}
