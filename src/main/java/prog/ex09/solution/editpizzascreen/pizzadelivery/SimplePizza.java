package prog.ex09.solution.editpizzascreen.pizzadelivery;

import java.util.List;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  @Override
  public int getPizzaId() {
    return 0;
  }

  @Override
  public List<Topping> getToppings() {
    return null;
  }

  @Override
  public PizzaSize getSize() {
    return null;
  }

  @Override
  public int getPrice() {
    return 0;
  }
}
