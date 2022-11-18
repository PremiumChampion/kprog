package prog.ex09.editpizzascreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.solution.editpizzascreen.gui.EditPizzaScreen;
import prog.ex09.solution.editpizzascreen.pizzadelivery.SimplePizzaDeliveryService;

public class PizzaOrderLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PizzaOrderLauncher.class);

  @Override
  public void start(final Stage stage) throws Exception {
    logger.info("Can't see something ...");
    TabPane root = new TabPane();

    // create the service and an order. Then add a pizza.
    PizzaDeliveryService service = new SimplePizzaDeliveryService();
    int orderId = service.createOrder();
    logger.info("Orderid = {}", orderId);
    int pizzaId = service.addPizza(orderId, PizzaSize.EXTRA_LARGE);
    logger.info("PizzaId = {}", pizzaId);

    // The pizza can now be edited
    Tab pizzaDeliveryTab = new Tab("EditPizzaScreen",
            new EditPizzaScreen(service, orderId, pizzaId));
    root.getTabs().add(pizzaDeliveryTab);

    stage.setTitle("KPROG JavaFX wonderful GUIs");
    stage.setScene(new Scene(root));
    stage.show();

  }
}
