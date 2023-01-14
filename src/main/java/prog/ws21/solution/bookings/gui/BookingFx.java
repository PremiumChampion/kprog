package prog.ws21.solution.bookings.gui;

import examples.javafx.modal.ExceptionAlert;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import prog.ws21.exercise.bookings.BookingManager;
import javafx.scene.layout.VBox;

/**
 * Visualizes a form allowing to enter data for a new booking.
 */
public class BookingFx extends VBox {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BookingFx.class);
  private final Label newBookingLabel = new Label();
  private final Label reasonLabel = new Label();
  private final Label priceLabel = new Label();
  private final Label sourceLabel = new Label();
  private final DatePicker datePicker = new DatePicker();
  private final TextField reasonTextField = new TextField();
  private final TextField priceTextField = new TextField();
  private final TextField sourceTextField = new TextField();
  private final GridPane table = new GridPane();
  private final Button addButton = new Button();

  private BookingManager bookingManager;

  public BookingFx(BookingManager bookingManager) {

    this.bookingManager = bookingManager;

    ResourceBundle r = ResourceBundle.getBundle("expenses");
    newBookingLabel.setText(r.getString("new.expense"));
    reasonLabel.setText(r.getString("reason"));
    priceLabel.setText(r.getString("price"));
    sourceLabel.setText(r.getString("source"));
    addButton.setText(r.getString("add"));

    addButton.setOnAction(this::addEntry);

    table.addColumn(0, newBookingLabel, reasonLabel, priceLabel, sourceLabel);
    table.addColumn(1, datePicker, reasonTextField, priceTextField, sourceTextField, addButton);

    getChildren().add(table);
  }

  private void addEntry(ActionEvent actionEvent) {
    try {
      LocalDate date = datePicker.getValue();
      String reason = reasonTextField.getText();
      int price = Integer.parseInt(priceTextField.getText());
      String source = sourceTextField.getText();
      bookingManager.addBooking(price, reason, source, date);
      datePicker.setValue(null);
      reasonTextField.setText("");
      priceTextField.setText("");
      sourceTextField.setText("");
    } catch (IllegalArgumentException e) {
      new ExceptionAlert(e).show();
      logger.info("Error occurred while adding a booking", e);
    }
  }


}
