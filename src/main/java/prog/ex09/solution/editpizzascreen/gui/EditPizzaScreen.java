package prog.ex09.solution.editpizzascreen.gui;

import javafx.scene.layout.VBox;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;

/**
 * JavaFX component to edit a pizza configuration.
 */
public class EditPizzaScreen extends VBox {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  public EditPizzaScreen(PizzaDeliveryService service, final int orderId, int pizzaId) {
  }
}
