package prog.ex11.saveandload;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaDeliveryService;
import prog.ex11.solution.saveandload.factory.PlainTextPersistenceFactory;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizzaDeliveryService;

public class TestPlainTextPersistenceFactoryBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestPlainTextPersistenceFactoryBadCases.class);
  private PlainTextPersistenceFactory factory;
  private PizzaDeliveryService service;
  private ClassLoader classLoader;

  @Before
  public void setup() {
    factory = new PlainTextPersistenceFactory();
    service = new SimplePizzaDeliveryService();
    classLoader = getClass().getClassLoader();
  }

  @Test(expected = WrongOrderFormatException.class)  @Ignore
  public void testLoadBadOrderFile1() throws IOException, WrongOrderFormatException {
    logger.info("try to load a bad file with a Topping which does not exist");
    Order order = factory.load(new File("testfiles/test-badorder-1.txt"));
  }


  @Test(expected = WrongOrderFormatException.class)  @Ignore
  public void testLoadBadOrderFile2() throws IOException, WrongOrderFormatException {
    logger.info("try to load a bad file with one Token too much in the first line");
    Order order = factory.load(new File("testfiles/test-badorder-2.txt"));
  }

  @Test(expected = WrongOrderFormatException.class) @Ignore
  public void testLoadBadOrderFile3() throws IOException, WrongOrderFormatException {
    logger.info("try to load a bad file with one Token too few in the first line");
    Order order = factory.load(new File("testfiles/test-badorder-3.txt"));
  }

  @Test(expected = WrongOrderFormatException.class) @Ignore
  public void testLoadBadOrderFile4() throws IOException, WrongOrderFormatException {
    logger.info("try to load a bad file with Strings instead of ids for order and pizza");
    Order order = factory.load(new File("testfiles/test-badorder-4.txt"));
  }

}
