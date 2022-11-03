package examples.javafx.modal;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionAlert extends Alert {
  private static final Logger logger =
      LoggerFactory.getLogger(ExceptionAlert.class);

  public ExceptionAlert(final Exception ex) {
    super(AlertType.ERROR);
    setTitle("What a Terrible Failure (WTF)");
    setHeaderText(ex.getClass().getSimpleName());
    setContentText(ex.getMessage());
  }
}
