package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex06.exercise.pizzadelivery.PizzaDeliveryService;
import prog.ex06.exercise.pizzadelivery.Topping;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Test cases for toppings.
 */
public class TestTopping {

  private static Logger logger = LoggerFactory.getLogger(TestTopping.class);
  private PizzaDeliveryService service;

  @Before
  public void setup() {
    this.service = new SimplePizzaDeliveryService();
  }

  @After
  public void teardown() {
  }

  @Test
  public void testPriseListTopping() {

    Map<Topping, Integer> previousPriceMap = this.service.getToppingsPriceList();

    assertNotNull("price map is null", previousPriceMap);

    try {
      previousPriceMap.remove(Topping.TOMATO);
    } catch (UnsupportedOperationException e) {
      // this is expected
    }

    Map<Topping, Integer> nextPriceMap = this.service.getToppingsPriceList();

    assertTrue("Price list should be immutable", nextPriceMap.containsKey(Topping.TOMATO));

    Map<Topping, Integer> priceMap = this.service.getToppingsPriceList();

    assertEquals("Price for Topping TOMATO should be 30ct.", priceMap.get(Topping.TOMATO),
        Integer.valueOf(30));
    assertEquals("Price for Topping CHEESE should be 60ct.", priceMap.get(Topping.CHEESE),
        Integer.valueOf(60));
    assertEquals("Price for Topping SALAMI should be 50ct.", priceMap.get(Topping.SALAMI),
        Integer.valueOf(50));
    assertEquals("Price for Topping HAM should be 70ct.", priceMap.get(Topping.HAM),
        Integer.valueOf(70));
    assertEquals("Price for Topping PINEAPPLE should be 90ct.", priceMap.get(Topping.PINEAPPLE),
        Integer.valueOf(90));
    assertEquals("Price for Topping VEGETABLES should be 20ct.", priceMap.get(Topping.VEGETABLES),
        Integer.valueOf(20));
    assertEquals("Price for Topping SEAFOOD should be 150ct.", priceMap.get(Topping.SEAFOOD),
        Integer.valueOf(150));
  }
}
