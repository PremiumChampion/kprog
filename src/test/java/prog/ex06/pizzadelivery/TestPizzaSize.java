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
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Test cases for pizza size.
 */
public class TestPizzaSize {

  private static Logger logger = LoggerFactory.getLogger(TestPizzaSize.class);
  PizzaDeliveryService service;

  @Before
  public void setup() {
    this.service = new SimplePizzaDeliveryService();
  }

  @After
  public void teardown() {
    this.service = null;
  }

  @Test
  public void testForImmutableSizeList(){
    Map<PizzaSize, Integer> previousPriceMap = this.service.getPizzaSizePriceList();

    assertNotNull("price map is null", previousPriceMap);

    try {
      previousPriceMap.remove(PizzaSize.SMALL);
    } catch (UnsupportedOperationException e) {
      // this is expected
    }

    Map<PizzaSize, Integer> nextPriceMap = this.service.getPizzaSizePriceList();

    assertTrue("Price list should be immutable", nextPriceMap.containsKey(PizzaSize.SMALL));
  }

  @Test
  public void testPriseListPizzaSize() {
    Map<PizzaSize, Integer> nextPriceMap = this.service.getPizzaSizePriceList();

    assertTrue("Price list should be immutable", nextPriceMap.containsKey(PizzaSize.SMALL));

    Map<PizzaSize, Integer> priceMap = this.service.getPizzaSizePriceList();

    assertEquals("Eine kleine Pizza sollte 500ct kosten.", priceMap.get(PizzaSize.SMALL),
        Integer.valueOf(500));
    assertEquals("Eine kleine Pizza sollte 700ct kosten.", priceMap.get(PizzaSize.MEDIUM),
        Integer.valueOf(700));
    assertEquals("Eine kleine Pizza sollte 900ct kosten.", priceMap.get(PizzaSize.LARGE),
        Integer.valueOf(900));
    assertEquals("Eine kleine Pizza sollte 1100ct kosten.", priceMap.get(PizzaSize.EXTRA_LARGE),
        Integer.valueOf(1100));
  }

}
