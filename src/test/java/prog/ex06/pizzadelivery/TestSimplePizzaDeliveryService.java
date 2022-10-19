package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex06.exercise.pizzadelivery.Order;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaDeliveryService;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.Topping;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Tests for simple delivery service.
 */
public class TestSimplePizzaDeliveryService {

  private static final Logger logger = LoggerFactory.getLogger(
      TestSimplePizzaDeliveryService.class);
  private PizzaDeliveryService deliveryService;


  @Before
  public void setup() {
    this.deliveryService = new SimplePizzaDeliveryService();
  }

  @After
  public void teardown() {
    this.deliveryService = null;
  }

  @Test
  public void testGetByOrderId() {
    int orderId = this.deliveryService.createOrder();
    try {
      Order deliveryServiceOrder = this.deliveryService.getOrder(orderId);
      assertNotNull(String.format("order with id %s is null", orderId), deliveryServiceOrder);
      int deliveryServiceOrderId = deliveryServiceOrder.getOrderId();
      assertEquals(String.format("orders do not match: \nrequested: %s\ngot:%s",
              deliveryServiceOrderId,
              deliveryServiceOrderId
          ),
          deliveryServiceOrderId,
          deliveryServiceOrderId
      );
    } catch (IllegalArgumentException e) {
      fail(String.format("order with id %s should be created an available", orderId));
    }
  }

  @Test
  public void testGetByInvalidOrderId() {
    try {
      this.deliveryService.getOrder(-1);
      fail("able to get order with invalid order id.");
    } catch (IllegalArgumentException e) {
      // expected
    }
  }


  @Test
  public void testAddPizzaToOrder() {
    int orderId = this.deliveryService.createOrder();
    Order order = this.deliveryService.getOrder(orderId);

    assertNotNull("order is null", order);

    List<Integer> createdPizzas = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      int pizzaId = 0;

      try {
        pizzaId = this.deliveryService.addPizza(orderId, PizzaSize.SMALL);
      } catch (IllegalArgumentException e) {
        fail("could not add pizza to order with valid id.");
      }

      if (createdPizzas.contains(pizzaId)) {
        fail("duplicate orderId");
      }

      createdPizzas.add(pizzaId);

      List<Pizza> orderList = order.getPizzaList();
      int finalPizzaId1 = pizzaId;
      boolean pizzaInOrder = orderList.stream()
          .anyMatch(pizza -> pizza.getPizzaId() == finalPizzaId1);
      assertTrue("the added pizza was not found in the order.", pizzaInOrder);
    }
  }

  @Test
  public void testRemovePizzaFromOrder() {
    int orderId = this.deliveryService.createOrder();
    Order order = this.deliveryService.getOrder(orderId);

    assertNotNull("order is null", order);

    List<Integer> createdPizzas = addDummyPizzaToOrder(this.deliveryService, orderId, 20);

    for (Integer createdPizza : createdPizzas) {
      try {
        this.deliveryService.removePizza(orderId, createdPizza);
        boolean pizzaInOrder = createdPizzas.contains(createdPizza);

        assertTrue("the added pizza was found in the order.", pizzaInOrder);

      } catch (IllegalArgumentException e) {
        fail(String.format("unable to remove pizza with id %s from order with id %s",
                orderId,
                createdPizza
            )
        );
      }
    }
    assertTrue("no pizza was removed from the list", order.getPizzaList().isEmpty());
  }


  @Test
  public void testRemovePizzaWithInvalidIdFromOrderWithInvalidId() {
    int orderId = this.deliveryService.createOrder();
    Order order = this.deliveryService.getOrder(orderId);

    assertNotNull("order is null", order);

    List<Integer> createdPizzas = addDummyPizzaToOrder(this.deliveryService, orderId, 20);

    Random r = new Random();
    int invalidId = r.nextInt();
    while (createdPizzas.contains(invalidId)) {
      invalidId = r.nextInt();
    }

    try {
      this.deliveryService.removePizza(orderId, -1);
      fail(String.format("can remove order with illegal pizza id (%s)", invalidId));
    } catch (IllegalArgumentException e) {
      // success
    }
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

  private List<Integer> createDummyOrders(PizzaDeliveryService service, int count) {
    List<Integer> createdOrders = new ArrayList<>();
    createdOrders.add(service.createOrder());
    return createdOrders;
  }

  private List<Integer> addDummyPizzaToOrder(PizzaDeliveryService service, int orderId, int count) {
    List<Integer> createdPizzas = new ArrayList<>();
    Order order = service.getOrder(orderId);

    assertNotNull("order is null", order);

    for (int i = 0; i < count; i++) {
      int pizzaId = 0;
      PizzaSize size = randomPizzaSize();
      try {
        pizzaId = service.addPizza(orderId, size);
      } catch (IllegalArgumentException e) {
        fail(String.format("could not add pizza to order with valid id (%s) of size (%s).", orderId,
            size));
      }
      int finalPizzaId = pizzaId;
      if (createdPizzas.stream().anyMatch(p -> p == finalPizzaId)) {
        fail("duplicate orderId");
      }

      createdPizzas.add(pizzaId);

      List<Pizza> orderList = order.getPizzaList();
      int finalPizzaId1 = pizzaId;
      boolean pizzaInOrder = orderList.stream()
          .anyMatch(pizza -> pizza.getPizzaId() == finalPizzaId1);
      assertTrue("the added pizza was not found in the order.", pizzaInOrder);
    }

    return createdPizzas;
  }

  @Test
  public void testRemovePizzaWithInvalidIdFromOrderWithId() {
    int orderId = this.deliveryService.createOrder();
    Order order = this.deliveryService.getOrder(orderId);

    assertNotNull("order is null", order);

    List<Integer> createdPizzas = addDummyPizzaToOrder(this.deliveryService, orderId, 20);

    Random r = new Random();
    int invalidId = r.nextInt();
    while (createdPizzas.contains(invalidId)) {
      invalidId = r.nextInt();
    }

    try {
      this.deliveryService.removePizza(orderId, invalidId);
      fail(String.format("can remove order with invalid pizza id (%s)", invalidId));
    } catch (IllegalArgumentException e) {
      // success
    }
  }

  @Test
  public void testInfluenceOfTwoPizzaDeliveryObjects() {
    PizzaDeliveryService service1 = new SimplePizzaDeliveryService();
    PizzaDeliveryService service2 = new SimplePizzaDeliveryService();

    int orderCount = 20;
    int pizzaCount = 20;

    List<Integer> ordersService1 = createDummyOrders(service1, orderCount);
    List<Integer> ordersService2 = createDummyOrders(service2, orderCount);

    Set<Integer> combinedOrders = new HashSet<>();
    combinedOrders.addAll(ordersService1);
    combinedOrders.addAll(ordersService2);
    int expectedOrderCount = ordersService1.size() + ordersService2.size();
    int combinedOrderCount = combinedOrders.size();

    assertEquals("order ids are not unique between services",
        expectedOrderCount,
        combinedOrderCount
    );

    List<Integer> pizzaListService1 = new ArrayList<>();
    List<Integer> pizzaListService2 = new ArrayList<>();

    for (int serviceOrders : ordersService1) {
      pizzaListService1.addAll(addDummyPizzaToOrder(service1, serviceOrders, pizzaCount));
    }

    for (int serviceOrders : ordersService2) {
      pizzaListService2.addAll(addDummyPizzaToOrder(service2, serviceOrders, pizzaCount));
    }

    Set<Integer> combinedPizzas = new HashSet<>();
    combinedPizzas.addAll(pizzaListService1);
    combinedPizzas.addAll(pizzaListService2);
    int expectedPizzaCount = pizzaListService1.size() + pizzaListService2.size();
    int combinedPizzaCount = combinedPizzas.size();

    assertEquals("pizza ids are not unique between services",
        expectedPizzaCount,
        combinedPizzaCount
    );
  }
}
