package prog.ex09.editpizzascreen;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.solution.editpizzascreen.gui.EditPizzaScreen;
import prog.ex09.solution.editpizzascreen.pizzadelivery.SimplePizzaDeliveryService;

public abstract class BaseTestEditPizzaScreen extends ApplicationTest {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BaseTestEditPizzaScreen.class);

  protected StackPane gui;
  protected EditPizzaScreen screen;
  protected PizzaDeliveryService service;
  protected int orderId;
  protected int pizzaId;

  @Override
  public void start(final Stage stage) throws Exception {
    super.start(stage);
    gui = new StackPane();
    stage.setScene(new Scene(gui, 600, 800));
    stage.show();
  }

  @BeforeClass
  public static void setupTestFX()
  {
//    // Only for ASB!!
//    System.setProperty("testfx.robot", "glass");
//    System.setProperty("testfx.headless", "true");
//    System.setProperty("prism.order", "sw");
//    System.setProperty("prism.text", "t2k");
  }


  @Before
  public void setup() throws TooManyToppingsException {
    service = new SimplePizzaDeliveryService();
    orderId = service.createOrder();
    pizzaId = service.addPizza(orderId, PizzaSize.SMALL);
    specificSetup();
    screen = new EditPizzaScreen(service, orderId, pizzaId);
    Platform.runLater(() -> {
      gui.getChildren().add(screen);
    });
    try {
      // Give the EDT time to add the screen to the gui.
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void specificSetup() throws TooManyToppingsException {
    // no specific setup
  }

  public void teardown() {
    Platform.runLater(() -> gui.getChildren().clear());
    specificTeardown();
  }

  private void specificTeardown() {
    // no specific teardown
  }
}
