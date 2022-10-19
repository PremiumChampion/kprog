package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex06.exercise.pizzadelivery.Order;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaDeliveryService;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.TooManyToppingsException;
import prog.ex06.exercise.pizzadelivery.Topping;
import prog.ex06.solution.pizzadelivery.SimplePizza;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;



/**
 * tests for simple pizza.
 */
public class TestSimplePizza {

  private static final Logger logger = LoggerFactory.getLogger(TestSimplePizza.class);

  private PizzaDeliveryService pizzaService;

  private int someOrderId;

  private List<Topping> duplicateToppings;
  private List<Topping> toppingsValidRange;
  private List<Topping> toppingsInvalidRange;

  /**
   * setup method.
   */
  @Before
  public void setup() {
    this.pizzaService = new SimplePizzaDeliveryService();

    this.someOrderId = this.pizzaService.createOrder();
    logger.info(String.format("Created order with id:%s", this.someOrderId));

    Topping[] allToppings = Topping.values();

    Random r = new Random();

    this.toppingsValidRange = new ArrayList<>();
    for (int i = 0; i < PizzaDeliveryService.MAX_TOPPINGS_PER_PIZZA; i++) {
      Topping randomTopping = allToppings[r.nextInt(allToppings.length)];
      this.toppingsValidRange.add(randomTopping);
    }

    this.toppingsInvalidRange = new ArrayList<>();
    for (int i = 0; i < PizzaDeliveryService.MAX_TOPPINGS_PER_PIZZA + 1; i++) {
      Topping randomTopping = allToppings[r.nextInt(allToppings.length)];
      this.toppingsInvalidRange.add(randomTopping);
    }

    this.duplicateToppings = new ArrayList<>();
    Topping randomTopping = allToppings[r.nextInt(allToppings.length)];
    for (int i = 0; i < PizzaDeliveryService.MAX_TOPPINGS_PER_PIZZA; i++) {
      this.duplicateToppings.add(randomTopping);
    }

  }

  /**
   * teardown method.
   */
  @After
  public void teardown() {
    this.pizzaService = null;
    this.toppingsInvalidRange = null;
    this.toppingsValidRange = null;

  }

  @Test
  public void testAddDuplicateToppings() {
    int pizzaId = this.pizzaService.addPizza(this.someOrderId, PizzaSize.SMALL);

    try {
      for (Topping toppingToAdd : this.duplicateToppings) {
        this.pizzaService.addTopping(pizzaId, toppingToAdd);
      }
    } catch (TooManyToppingsException e) {
      fail("the number of toppings added lies within the valid range.");
    }

    Order order = this.pizzaService.getOrder(this.someOrderId);

    assertNotNull("order is null", order);

    Pizza pizza = order.getPizzaList().get(0);

    assertNotNull("pizza is null", pizza);

    Topping[] presentPizzaToppings = pizza.getToppings().toArray(new Topping[0]);
    Arrays.sort(presentPizzaToppings);

    Topping[] requiredPizzaToppings = this.duplicateToppings.toArray(new Topping[0]);
    Arrays.sort(requiredPizzaToppings);

    assertArrayEquals("The toppings were not added to the pizza.",
        presentPizzaToppings,
        requiredPizzaToppings
    );
  }

  @Test
  public void testAddTopping() {
    logger.info("Trying to add toppings to a pizza");
    Pizza pizza = new SimplePizza(this.pizzaService, PizzaSize.SMALL);

    //test for list immutability
    for (Topping toppingToAdd : this.toppingsValidRange) {

      List<Topping> toppings = pizza.getToppings();
      assertNotNull("toppings are null", toppings);
      try {
        toppings.add(toppingToAdd);
        fail("mutable list");
      } catch (UnsupportedOperationException e) {
        // this is expected
      }
    }

    Topping[] expectedToppings = new Topping[0];
    List<Topping> presentPizzaToppings = pizza.getToppings();

    assertNotNull("toppings are null", presentPizzaToppings);

    assertArrayEquals("Topping can be added from the outside of the list.",
        presentPizzaToppings.toArray(),
        expectedToppings
    );

    for (int i = 0; i < 5; i++) {
      int pizzaId = this.pizzaService.addPizza(this.someOrderId, PizzaSize.SMALL);

      try {
        for (Topping toppingToAdd : this.toppingsValidRange) {
          this.pizzaService.addTopping(pizzaId, toppingToAdd);
        }

      } catch (TooManyToppingsException e) {
        fail("The number of toppings added lies within the valid range.");
      }

      pizza = this.pizzaService.getOrder(this.someOrderId).getPizzaList().get(i);

      Topping[] presentPizzaToppingsArray = pizza.getToppings().toArray(new Topping[0]);
      Arrays.sort(presentPizzaToppingsArray);

      Topping[] requiredPizzaToppingsArray = this.toppingsValidRange.toArray(new Topping[0]);
      Arrays.sort(requiredPizzaToppingsArray);

      assertArrayEquals("The toppings were not added to the pizza.",
          presentPizzaToppingsArray,
          requiredPizzaToppingsArray
      );
    }
  }

  @Test
  public void testRemoveTopping() {
    logger.info("Tying to remove toppings from a pizza");

    int pizzaId = this.pizzaService.addPizza(this.someOrderId, PizzaSize.SMALL);

    try {
      for (Topping toppingToAdd : this.toppingsValidRange) {
        this.pizzaService.addTopping(pizzaId, toppingToAdd);
      }
    } catch (TooManyToppingsException e) {
      fail("The number of toppings added lies within the valid range.");
    }

    Order order = this.pizzaService.getOrder(this.someOrderId);

    assertNotNull("order is null", order);
    List<Pizza> pizzaList = order.getPizzaList();
    assertNotNull("pizzalist is null", pizzaList);

    Pizza pizza = pizzaList.get(0);

    Topping[] prevPizzaToppings = pizza.getToppings().toArray(new Topping[0]);
    Arrays.sort(prevPizzaToppings);

    //test for list immutability
    try {
      pizza.getToppings().remove(0);
      fail("mutable list");
    } catch (UnsupportedOperationException e) {
      // this is expected
    }

    Topping[] nextPizzaToppings = pizza.getToppings().toArray(new Topping[0]);
    Arrays.sort(nextPizzaToppings);

    assertArrayEquals("The toppings were not removed from the pizza.",
        prevPizzaToppings,
        nextPizzaToppings
    );

    Topping[] presentPizzaToppings = pizza.getToppings().toArray(new Topping[0]);
    Arrays.sort(presentPizzaToppings);

    Topping[] requiredPizzaToppings = this.toppingsValidRange.toArray(new Topping[0]);
    Arrays.sort(requiredPizzaToppings);

    assertArrayEquals("The toppings were not added to the pizza.",
        presentPizzaToppings,
        requiredPizzaToppings
    );

    List<Topping> currentToppings = new ArrayList<>(this.toppingsValidRange);
    while (currentToppings.size() > 0) {
      Topping toppingToRemove = currentToppings.get(0);
      this.pizzaService.removeTopping(pizzaId, toppingToRemove);
      currentToppings.remove(toppingToRemove);

      Topping[] presentPizzaToppingsAfterRemoval = pizza.getToppings().toArray(new Topping[0]);
      Arrays.sort(presentPizzaToppingsAfterRemoval);

      Topping[] requiredPizzaToppingsAfterRemoval = currentToppings.toArray(new Topping[0]);
      Arrays.sort(requiredPizzaToppingsAfterRemoval);

      assertArrayEquals(String.format("The topping %s was not removed properly.", toppingToRemove),
          presentPizzaToppingsAfterRemoval,
          requiredPizzaToppingsAfterRemoval
      );
    }
  }

  @Test
  public void testAddToManyToppings() {
    int pizzaId = this.pizzaService.addPizza(this.someOrderId, PizzaSize.SMALL);

    try {
      for (Topping toppingToAdd : this.toppingsInvalidRange) {
        this.pizzaService.addTopping(pizzaId, toppingToAdd);
      }
      fail("The number of toppings added lies within the invalid range.");
    } catch (TooManyToppingsException e) {
      // this is expected
    } catch (Exception e) {
      fail("Something else went wrong: " + e.getMessage());
    }

  }
}
