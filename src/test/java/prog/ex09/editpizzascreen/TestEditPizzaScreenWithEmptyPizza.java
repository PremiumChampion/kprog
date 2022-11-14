package prog.ex09.editpizzascreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import org.junit.Test;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

public class TestEditPizzaScreenWithEmptyPizza extends BaseTestEditPizzaScreen {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestEditPizzaScreenWithEmptyPizza.class);


  @Test
  public void checkThatSizeLabelHasCorrectSize() throws InterruptedException {
    Thread.sleep(1000);
    Label label = lookup("#pizzaSizeLabel").query();
    assertNotNull("Cannot find the pizza size label which should be named \"pizzaSizeLabel\"",
            label);
    String labelText = label.getText();
    logger.info("PizzaSizeLabel has Text: " + label.getText());
    assertTrue("PizzaSizeLabel should show the size SMALL",
            labelText.toLowerCase().contains(
            "small"));
  }

  @Test
  public void checkThatPrizeLabelHasCorrectPrice() throws InterruptedException {
    Thread.sleep(1000);
    Label priceLabel = lookup("#priceLabel").query();
    assertNotNull("Cannot find the price label which should be named \"priceLabel\"",
            priceLabel);
    String labelText = priceLabel.getText();
    logger.info("PriceLabel has Text: " + priceLabel.getText());
    assertTrue("PriceLabel should show the price for a small pizza without toppings",
    labelText.contains("500") || labelText.contains("5") );
  }

  @Test
  public void checkThatToppingChoiceBoxHasCorrectNumberOfElements() throws InterruptedException {
    Thread.sleep(1000);
    ChoiceBox<Topping> choiceBox = lookup("#toppingChoiceBox").query();
    assertNotNull("Cannot find the choice box which should be named \"toppingChoiceBox\"",
            choiceBox);
    assertEquals("All toppings should be in the choiceBox", choiceBox.getItems().size(),
            Topping.values().length);

  }

  @Test
  public void checkThatPizzaToppingListIsEmpty() throws InterruptedException {
    Thread.sleep(1000);
    ListView<Topping> listView = lookup("#toppingsOnPizzaListView").queryListView();
    assertNotNull("Cannot find the list view which should be named \"toppingsOnPizzaListView\"",
            listView);
    assertEquals("The list view with the toppings of the pizza should be empty", 0,
            listView.getItems().size());
  }

  @Test
  public void addAToppingAndCheckTheListView() throws InterruptedException {
    Thread.sleep(1000);
    ChoiceBox<Topping> choiceBox = lookup("#toppingChoiceBox").query();
    assertNotNull("Cannot find the choice box which should be named \"toppingChoiceBox\"",
            choiceBox);
    Button button = lookup("#addToppingButton").queryButton();
    assertNotNull("Cannot find the button which should be named \"addToppingButton\"",
            button);

    Platform.runLater(() -> {
      clickOn(choiceBox);
      type(KeyCode.DOWN);
      type(KeyCode.ENTER);
      clickOn(button);
    });

    Thread.sleep(2000);

    ListView<Topping> listView = lookup("#toppingsOnPizzaListView").queryListView();
    assertNotNull("Cannot find the list view which should be named \"toppingsOnPizzaListView\"",
            listView);
    assertEquals("The list view with the toppings of the pizza should have one entry", 1,
            listView.getItems().size());

  }
}
