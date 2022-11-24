package examples.javafx.modal;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Alert extension to show exceptions to the user using a modal dialog.
 */
public class ExceptionAlert extends Alert {
  private static final Logger logger =
      LoggerFactory.getLogger(ExceptionAlert.class);

  /**
   * Constructor.
   *
   * @param ex exception to be shown
   */
  public ExceptionAlert(final Exception ex) {
    super(AlertType.ERROR);
    setTitle("What a Terrible Failure (WTF)");
    setHeaderText(ex.getClass().getSimpleName());
    setContentText(ex.getMessage());
  }
}
