package prog.ex10.exercise.javafx4pizzadelivery.gui;

/**
 * Switches the screens of an application, usually in a certain display area, usually a Node.
 */
public interface ScreenController {

  /**
   * Triggers the exchange of Screens.
   *
   * @param fromScreen The screen which triggers the switch. For the first screen this may be null.
   * @param toScreen   The screen to switch to. This must always be readable string.
   * @throws UnknownTransitionException if the transition between the given screens is not
   *                                    specified
   */
  void switchTo(String fromScreen, String toScreen) throws UnknownTransitionException;
}
