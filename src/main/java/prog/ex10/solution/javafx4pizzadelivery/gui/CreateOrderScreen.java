package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.scene.layout.VBox;

/**
 * Screen to create an order in the PizzaDeliveryService.
 */
public class CreateOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";

  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {
  }
}
