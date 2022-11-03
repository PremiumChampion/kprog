package examples.javafx.firststeps;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 * Handler to place the name of the current thread into a given label.
 */
public class ButtonHandler implements EventHandler<ActionEvent> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ButtonHandler.class);

  private final Label label;

  public ButtonHandler(final Label label) {
    this.label = label;
  }

  @Override
  public void handle(final ActionEvent event) {
    logger.info("Current thread is {}.", Thread.currentThread());
    label.setText(Thread.currentThread().getName());
    event.consume();
  }
}
