package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * launcher.
 */
public class PizzaDeliveryLauncher extends Application {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaDeliveryLauncher.class);


  @Override
  public void start(final Stage stage) throws Exception {
    logger.info("Can't see something ...");
    TabPane root = new TabPane();
    Tab welcomeTab = new Tab("Welcome", new Label("Your advertisement could be here."));
    root.getTabs().add(welcomeTab);

    SingletonAttributeStore attributeStore = SingletonAttributeStore.getInstance();
    PizzaDeliveryService service = new SimplePizzaDeliveryService();
    try {
      // should additional properties be set here?
      attributeStore.setAttribute(SingletonAttributeStore.PIZZA_DELIVERY_SERVICE, service);
      attributeStore.setAttribute(SingletonAttributeStore.PIZZA_ID, new SimpleIntegerProperty(-1));
      attributeStore.setAttribute(SingletonAttributeStore.ORDER_ID, new SimpleIntegerProperty(-1));
    } catch (Exception e) {
      logger.error("attributeStore");
    }
    Pane paneToBeFilled = new Pane();
    paneToBeFilled.getChildren().add(new Label("Your advertisement could be here ..."));
    Tab pizzaDeliveryTab = new Tab("PizzaDeliveryService", paneToBeFilled);
    ScreenController controller = new PizzaDeliveryScreenController(paneToBeFilled);
    controller.switchTo(null, CreateOrderScreen.SCREEN_NAME);
    root.getTabs().add(pizzaDeliveryTab);

    stage.setTitle("KPROG JavaFX wonderful GUIs");
    stage.setScene(new Scene(root));
    stage.show();

  }
}
