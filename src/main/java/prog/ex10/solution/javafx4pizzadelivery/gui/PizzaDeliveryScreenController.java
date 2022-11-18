package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Builder;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;

/**
 * Simple and straight-forward implementation of a ScreenController for the PizzaDeliveryService.
 */
public class PizzaDeliveryScreenController implements ScreenController {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PizzaDeliveryScreenController.class);
  private final Pane pane;
  private final Map<String, Builder<Node>> scenes;
  private final Map<String, List<String>> validTransitions;

  /**
   * Screen controller.
   *
   * @param pane root element.
   */
  public PizzaDeliveryScreenController(final Pane pane) {
    this.pane = pane;
    this.scenes = new HashMap<>();
    this.validTransitions = new HashMap<>();
    this.addValidTransition(null, CreateOrderScreen.SCREEN_NAME,
        () -> new CreateOrderScreen(this));
    this.addValidTransition(CreateOrderScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME,
        () -> new ShowOrderScreen(this));
    this.addValidTransition(ShowOrderScreen.SCREEN_NAME, CreateOrderScreen.SCREEN_NAME,
        () -> new CreateOrderScreen(this));
    this.addValidTransition(ShowOrderScreen.SCREEN_NAME, EditPizzaScreen.SCREEN_NAME,
        () -> new EditPizzaScreen(this));
    this.addValidTransition(EditPizzaScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME,
        () -> new ShowOrderScreen(this));

  }


  @Override
  public void switchTo(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {
    assertValidTransition(fromScreen, toScreen);
    this.pane.getChildren().clear();
    this.pane.getChildren().add(scenes.get(toScreen).build());
  }

  /**
   * checks for valid transition.
   *
   * @param fromScreen from.
   * @param toScreen   to.
   * @throws UnknownTransitionException u know when
   */
  private void assertValidTransition(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {
    if (toScreen == null) {
      throw new UnknownTransitionException("Target screen is NULL.", fromScreen, toScreen);
    }

    if (toScreen.trim().length() == 0) {
      throw new UnknownTransitionException(String.format("Invalid name: '%s'.", toScreen),
          fromScreen, toScreen);
    }

    if (!this.validTransitions.containsKey(fromScreen)) {
      throw new UnknownTransitionException("unknown transition", fromScreen, toScreen);
    }

    List<String> allowedTransitionsFromScreen = this.validTransitions.get(fromScreen);
    if (!allowedTransitionsFromScreen.contains(toScreen)) {
      throw new UnknownTransitionException("invalid transition", fromScreen, toScreen);
    }
  }

  /**
   * adds a valid transition to the controller.
   *
   * @param fromScreen from.
   * @param toScreen   to.
   * @param screen     ui.
   */
  private void addValidTransition(final String fromScreen, final String toScreen,
      final Builder<Node> screen) {
    List<String> screenlist = this.validTransitions.computeIfAbsent(fromScreen,
        k -> new ArrayList<>());

    screenlist.add(toScreen);
    this.scenes.computeIfAbsent(toScreen, k -> screen);
  }

}
