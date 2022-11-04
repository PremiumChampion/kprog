package prog.ex09.exercise.editpizzascreen.pizzadelivery;

import java.util.List;

/**
 * A Pizza represents a pizza from the view of a pizza delivery service. It consists of
 *<ul>
 *   <li>a list of Toppings</li>
 *   <li>a size</li>
 *   <li>a price</li>
 *</ul>
 */
public interface Pizza {

  /**
   * Returns the id of the pizza. The id of the pizza must be unique within the
   * PizzaDeliveryService.
   *
   * @return id of the pizza
   */
  int getPizzaId();

  /**
   * Returns a list of Toppings the pizza has. Toppings are allowed to appear multiple times.
   *
   * @return List of Toppings configured for this pizza
   */
  List<Topping> getToppings();

  /**
   * Returns the size of the pizza.
   *
   * @return Size of the pizza
   */
  PizzaSize getSize();

  /**
   * Returns the cumulated price (base price for the size plus toppings) of this pizza.
   *
   * @return cumulated price of the pizza
   */
  int getPrice();
}
