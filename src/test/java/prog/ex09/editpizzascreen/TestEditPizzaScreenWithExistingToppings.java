package prog.ex09.editpizzascreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.junit.Test;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;


public class TestEditPizzaScreenWithExistingToppings extends BaseTestEditPizzaScreen {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestEditPizzaScreenWithExistingToppings.class);

  @Override
  public void specificSetup() throws TooManyToppingsException {
    service.addTopping(pizzaId, Topping.CHEESE);
    service.addTopping(pizzaId, Topping.HAM);
    service.addTopping(pizzaId, Topping.PINEAPPLE);
    logger.info("Setup: Three toppings added.");
  }

  @Test
  public void checkThatThereAreThreeToppingsOnThePizza() throws InterruptedException {
    Thread.sleep(1000);
    ListView<Topping> listView = lookup("#toppingsOnPizzaListView").queryListView();
    assertNotNull("Cannot find the list view which should be named \"toppingsOnPizzaListView\"",
            listView);
    assertEquals("The list view with the toppings of the pizza should contain 3 toppings", 3,
            listView.getItems().size());
  }


  @Test
  public void tryToRemoveTheHamTopping() throws InterruptedException {
    Thread.sleep(1000);
    logger.info("Try removing the HAM.");
    Button removeButtonForHam = lookup("#remove-" + Topping.HAM.toString()).queryButton();
    Platform.runLater(() -> {
      clickOn(removeButtonForHam);
    });

    Thread.sleep(2000);

    ListView<Topping> listView = lookup("#toppingsOnPizzaListView").queryListView();
    assertNotNull("Cannot find the list view which should be named \"toppingsOnPizzaListView\"",
        listView);
    assertEquals("The list view with the toppings of the pizza should now contain 2 toppings", 2,
        listView.getItems().size());
  }
}
