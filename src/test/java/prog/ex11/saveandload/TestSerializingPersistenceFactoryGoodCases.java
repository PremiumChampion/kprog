package prog.ex11.saveandload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaDeliveryService;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.TooManyToppingsException;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.factory.SerializingPersistenceFactory;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizzaDeliveryService;

public class TestSerializingPersistenceFactoryGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSerializingPersistenceFactoryGoodCases.class);
  private PersistenceFactory factory;
  private PizzaDeliveryService service;
  private Order order;

  @Before
  public void setup() throws TooManyToppingsException {
    factory = new SerializingPersistenceFactory();
    service = new SimplePizzaDeliveryService();
    int orderId = service.createOrder();
    int pizzaId1 = service.addPizza(orderId, PizzaSize.EXTRA_LARGE);
    int pizzaId2 = service.addPizza(orderId, PizzaSize.MEDIUM);
    service.addTopping(pizzaId1, Topping.TOMATO);
    service.addTopping(pizzaId1, Topping.CHEESE);
    service.addTopping(pizzaId1, Topping.CHEESE);
    order = service.getOrder(orderId);

  }

  @Test @Ignore
  public void saveAndLoadAnOrder() throws IOException, WrongOrderFormatException {
    factory.save(new File("testorder.bin"), order);
    Order loadedOrder = factory.load(new File("testorder.bin"));
    String orderString = order.toString();
    String loadedOrderString = loadedOrder.toString();
    assertNotSame(order, loadedOrder);
    assertTrue(EqualityChecker.equals(order, loadedOrder));
    assertEquals(orderString, loadedOrderString);
  }
}
