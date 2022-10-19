package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;


/**
 * order tests.
 */
public class TestSimpleOrder {

  private static final Logger logger = LoggerFactory.getLogger(TestSimpleOrder.class);
  private PizzaDeliveryService pizzaService;

  @Before
  public void setup() {
    this.pizzaService = new SimplePizzaDeliveryService();
  }

  private PizzaSize randomPizzaSize() {
    PizzaSize[] sizes = PizzaSize.values();
    Random r = new Random();
    return sizes[r.nextInt(sizes.length)];
  }

  private Topping randomTopping() {
    Topping[] toppings = Topping.values();
    Random r = new Random();
    return toppings[r.nextInt(toppings.length)];
  }

  @After
  public void teardown() {
    this.pizzaService = null;
  }

  @Test
  public void testTwoEmptyOrderObjects() {
    int orderId1 = this.pizzaService.createOrder();
    int orderId2 = this.pizzaService.createOrder();
    assertNotEquals("two orders with the same id were created", orderId1, orderId2);

    try {
      Order order1 = this.pizzaService.getOrder(orderId1);
      Order order2 = this.pizzaService.getOrder(orderId2);
      assertNotEquals("two identical orders were created", order1, order2);
    } catch (IllegalArgumentException e) {
      fail(String.format("Two objects should be created with the ids [%s;%s]", orderId1, orderId2));
    }
  }

  @Test
  public void testEmptyPizzaList() {
    int emptyOrderId = this.pizzaService.createOrder();
    Order order = this.pizzaService.getOrder(emptyOrderId);
    assertNotNull("order is null", order);
    List<Pizza> orderList = order.getPizzaList();

    assertTrue("a empty list of pizza is expected",
        orderList.isEmpty()
    );
  }

  @Test
  public void testCalculatePizzaPrice01() {
    int orderId = this.pizzaService.createOrder();
    Order order = this.pizzaService.getOrder(orderId);

    assertNotNull("order is null", order);

    int total = 0;

    List<Integer> createdPizzas = new ArrayList<>();
    Random r = new Random();
    for (int i = 0; i < 20; i++) {
      // create pizza
      PizzaSize pizzaSizeToCreate = randomPizzaSize();
      int pizzaId = this.pizzaService.addPizza(orderId, pizzaSizeToCreate);

      Integer pizzaBasePrice = this.pizzaService.getPizzaSizePriceList().get(pizzaSizeToCreate);
      assertNotNull("The pizza price is null", pizzaBasePrice);
      total += pizzaBasePrice;

      if (createdPizzas.stream().anyMatch(p -> p == pizzaId)) {
        fail("duplicate orderId");
        return;
      }

      createdPizzas.add(pizzaId);
      // create pizza price

      for (int j = 0; j < r.nextInt(PizzaDeliveryService.MAX_TOPPINGS_PER_PIZZA + 1); j++) {
        Topping toppingToAdd = randomTopping();

        try {
          Integer toppingPrice = this.pizzaService.getToppingsPriceList().get(toppingToAdd);
          logger.info(String.format("Adding topping %s with price %s to pizza %s",
                  toppingToAdd,
                  toppingPrice,
                  pizzaId
              )
          );

          this.pizzaService.addTopping(pizzaId, toppingToAdd);

          assertNotNull("The pizza price is null", toppingPrice);

          total += toppingPrice;

        } catch (TooManyToppingsException | IllegalArgumentException e) {
          logger.info(
              String.format("Can not add topping '%s' to pizza with id '%s'",
                  toppingToAdd,
                  pizzaId
              )
          );

          fail("The number of toppings added lies within the invalid range.");
          return;
        }
      }
    }

    assertEquals(
        String.format("Total price (%s) differs from expected price (%s)", total, order.getValue()),
        total,
        order.getValue()
    );


  }

  @Test
  public void testCalculatePizzaPrice02() {

    List<Integer> orderIds = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      orderIds.add(this.pizzaService.createOrder());
    }

    for (Integer orderId : orderIds) {
      Order order = this.pizzaService.getOrder(orderId);
      int total = 0;

      Random r = new Random();

      List<Integer> createdPizzas = new ArrayList<>();

      for (int k = 0; k < 20; k++) {
        // create pizza
        PizzaSize pizzaSizeToCreate = randomPizzaSize();
        Map<PizzaSize, Integer> priceMap = this.pizzaService.getPizzaSizePriceList();
        assertNotNull("pizza size price mal is null", priceMap);

        Integer pizzaBasePrice = priceMap.get(pizzaSizeToCreate);
        assertNotNull("the pizza price is null", pizzaBasePrice);

        total += pizzaBasePrice;

        int pizzaId = this.pizzaService.addPizza(orderId, pizzaSizeToCreate);

        if (createdPizzas.stream().anyMatch(p -> p == pizzaId)) {
          fail("duplicate orderId");
          return;
        }

        createdPizzas.add(pizzaId);
        // create pizza price

        for (int j = 0; j < r.nextInt(PizzaDeliveryService.MAX_TOPPINGS_PER_PIZZA + 1); j++) {
          Topping toppingToAdd = randomTopping();

          try {
            Integer toppingPrice = this.pizzaService.getToppingsPriceList().get(toppingToAdd);
            logger.info(String.format("Adding topping %s with price %s to pizza %s",
                    toppingToAdd,
                    toppingPrice,
                    pizzaId
                )
            );

            this.pizzaService.addTopping(pizzaId, toppingToAdd);

            assertNotNull("The pizza price is null", toppingPrice);

            total += toppingPrice;

          } catch (TooManyToppingsException e) {
            logger.info(
                String.format("Can not add topping '%s' to pizza with id '%s'",
                    toppingToAdd,
                    pizzaId
                )
            );

            fail("The number of toppings added lies within the invalid range.");
            return;
          }
        }
      }

      assertEquals(
          String.format("Total price (%s) differs from expected price (%s)", total,
              order.getValue()),
          total,
          order.getValue()
      );
    }
  }
}
