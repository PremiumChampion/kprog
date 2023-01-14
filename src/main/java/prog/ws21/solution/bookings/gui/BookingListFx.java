package prog.ws21.solution.bookings.gui;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import prog.ws21.exercise.bookings.Booking;
import prog.ws21.exercise.bookings.BookingManager;

/**
 * Visualizes the list of Bookings of a BookingManager.
 */
public class BookingListFx extends VBox {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      BookingListFx.class);

  private BookingManager bookingManager;
  private ListView<Booking> bookingListView = new ListView<>();

  /**
   * Constructor.
   *
   * @param bookingManager BookingManager to get the list of bookings from
   */
  public BookingListFx(final BookingManager bookingManager) {
    this.bookingManager = bookingManager;
    this.bookingManager.addPropertyChangeListener(this::bookingsChanged);

    this.bookingListView.setCellFactory(i -> new BookingListCell());
    updateBookingList();

    this.getChildren().add(bookingListView);

  }

  private void updateBookingList() {
    this.bookingListView.getItems().clear();
    this.bookingListView.getItems().addAll(this.bookingManager.getBookingList());
  }

  private void bookingsChanged(PropertyChangeEvent propertyChangeEvent) {
    Platform.runLater(this::updateBookingList);
  }

  private class BookingListCell extends ListCell<Booking> {

    @Override
    protected void updateItem(Booking item, boolean empty) {
      super.updateItem(item, empty);
      setText(null);
      if (item == null || empty) {
        setGraphic(null);
        return;
      }

      HBox root = new HBox();
      VBox dateAndMoney = new VBox();
      VBox sourceAndReason = new VBox();
      root.getChildren().addAll(dateAndMoney, sourceAndReason);

      Label dateLabel = new Label();
      dateLabel.setText(item.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
      dateAndMoney.getChildren().add(dateLabel);

      Label moneyLabel = new Label();
      moneyLabel.setText(NumberFormat.getNumberInstance().format(item.getAmount()));
      dateAndMoney.getChildren().add(moneyLabel);

      Label sourceLabel = new Label();
      sourceLabel.setText(item.getSource());
      sourceAndReason.getChildren().add(sourceLabel);

      Label reasonLabel = new Label();
      reasonLabel.setText(item.getComment());
      sourceAndReason.getChildren().add(reasonLabel);
      setGraphic(root);
    }
  }
}
