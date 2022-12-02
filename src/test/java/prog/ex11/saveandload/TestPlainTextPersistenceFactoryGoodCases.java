package prog.ex11.saveandload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaDeliveryService;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.TooManyToppingsException;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.factory.PlainTextPersistenceFactory;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizzaDeliveryService;

public class TestPlainTextPersistenceFactoryGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestPlainTextPersistenceFactoryGoodCases.class);
  private PlainTextPersistenceFactory factory;
  private PizzaDeliveryService service;
  private ClassLoader classLoader;

  @Before
  public void setup() {
    factory = new PlainTextPersistenceFactory();
    service = new SimplePizzaDeliveryService();
    classLoader = getClass().getClassLoader();
  }
  @Test @Ignore
  public void testWithGoodOrder() throws TooManyToppingsException, IOException, WrongOrderFormatException {
int orderId = service.createOrder();
int pizzaId1 = service.addPizza(orderId, PizzaSize.EXTRA_LARGE);
int pizzaId2 = service.addPizza(orderId, PizzaSize.MEDIUM);
    service.addTopping(pizzaId1, Topping.TOMATO);
    service.addTopping(pizzaId1, Topping.CHEESE);
    service.addTopping(pizzaId1, Topping.CHEESE);
Order order = service.getOrder(orderId);
String filename = "order-" + order.getOrderId() + ".txt";
factory.save(new File(filename), order);

Stream<String> inStream = Files.lines(new File(filename).toPath());
String[] orderLines = inStream.toArray(String[]::new);
assertNotNull("Did not read the file properly", orderLines);
assertEquals("The amount of lines read is not correct, should be 3 but was: "+orderLines.length,3, orderLines.length);
checkFirstLine(orderLines[0]);
checkPizzaLine(orderLines[1]);
checkPizzaLine(orderLines[2]);
  }

  private void checkFirstLine(final String orderLine) throws WrongOrderFormatException {
    StringTokenizer parts = new StringTokenizer(orderLine, ";");
    if (parts.countTokens() != 3) {
      throw new WrongOrderFormatException("First line has to have three tokens");
    }

    assertThatIsInteger(parts.nextToken());
    assertThatIsInteger(parts.nextToken());
    assertThatIsInteger(parts.nextToken());

  }

  private void checkPizzaLine(final String line) throws WrongOrderFormatException {
    StringTokenizer parts = new StringTokenizer(line, ";");
    if (parts.countTokens() < 3) {
      throw new WrongOrderFormatException("Too few tokens in pizza line: "+ line);
    }

    assertThatIsInteger(parts.nextToken());
    assertThatIsInteger(parts.nextToken());
    assertTrue("Did not save ingredients properly", parts.nextToken().trim().length() > 1);

  }
  private void assertThatIsInteger(final String nextToken) throws WrongOrderFormatException {
    try {
      int value = Integer.parseInt(nextToken);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException("Value is not an Integer: " + nextToken, e);
    }
  }

  @Test @Ignore
  public void testLoadAGoodOrderFile() throws IOException, WrongOrderFormatException {
    Order order = factory.load(new File("testfiles/test-order-1.txt"));
    assertEquals("Expect order id to be 23 but was "+order.getOrderId()+".", 23, order.getOrderId());
    List<Pizza> pizzaList = order.getPizzaList();
    assertEquals("Order should contain 2 pizza but contains "+pizzaList.size(), 2, pizzaList.size());
    assertEquals("Complete order costs are 1888 cent.", 1888, order.getValue());

    // store all pizza into a map for easier access to pizza with certain id
    Map<Integer, Pizza> pizzaMap = new HashMap<>();
    for (Pizza pizza : pizzaList) {
      pizzaMap.put(pizza.getPizzaId(), pizza);
    }

    Pizza pizza42 = pizzaMap.get(42);
    assertNotNull("There must be a pizza with id 42",pizza42 );
    assertEquals("Pizza 42 has three toppings of two different kinds.",
            3, pizza42.getToppings().size());
    assertEquals("Pizza 42 costs 1111 cent.", 1111, pizza42.getPrice());

    Pizza pizza43 = pizzaMap.get(43);
    assertNotNull("There must be a pizza with id 43",pizza43 );
    assertEquals("Pizza 43 has no toppings",
            0, pizza43.getToppings().size());
    assertEquals("Pizza 43 costs 777 cent.", 777, pizza43.getPrice());
  }


  @Test @Ignore
  public void testLoadAGoodOrderFile2() throws IOException, WrongOrderFormatException {
    Order order = factory.load(new File("testfiles/test-order-2.txt"));
    assertNotNull(order);
    assertEquals("Expect order id to be 333 but was "+order.getOrderId()+".", 333, order.getOrderId());
    List<Pizza> pizzaList = order.getPizzaList();
    assertEquals("Order should contain 3 pizza but contains "+pizzaList.size()+".", 3, pizzaList.size());
    assertEquals("Complete order costs are 2600 cent.", 2600, order.getValue());

    // store all pizza into a map for easier access to pizza with certain id
    Map<Integer, Pizza> pizzaMap = new HashMap<>();
    for (Pizza pizza : pizzaList) {
      pizzaMap.put(pizza.getPizzaId(), pizza);
    }

    Pizza pizza980 = pizzaMap.get(980);
    assertNotNull("There must be a pizza with id 980 but it wasn't loaded.",pizza980 );
    assertEquals("Pizza 980 has three toppings of two different kinds.",
            3, pizza980.getToppings().size());
    assertEquals("Pizza 980 costs 1200 cent.", 1200, pizza980.getPrice());

    Pizza pizza984 = pizzaMap.get(984);
    assertNotNull("There must be a pizza with id 984 but it wasn't loaded.",pizza984 );
    assertEquals("Pizza 984 has 3 toppings.",
            3, pizza984.getToppings().size());
    assertEquals("Pizza 984 costs 800 cent.", 800, pizza984.getPrice());

    Pizza pizza988 = pizzaMap.get(988);
    assertNotNull("There must be a pizza with id 988 but wasn't loaded.",pizza988 );
    assertEquals("Pizza 988 has 1 topping.",
            1, pizza988.getToppings().size());
    assertEquals("Pizza 988 costs 600 cent.", 600, pizza988.getPrice());
  }

}
