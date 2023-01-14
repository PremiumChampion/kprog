package prog.ws21.solution.bookings.gui;

import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prog.ws21.exercise.bookings.BookingGenerator;
import prog.ws21.exercise.bookings.BookingManager;
import prog.ws21.exercise.bookings.SimpleBookingManager;

/**
 * Launches the Expense managing application.
 */
public class BookingLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BookingLauncher.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Locale.setDefault(new Locale("de", "DE"));
    BookingManager bookingManager = new SimpleBookingManager();
    // Fill in some test data
    BookingGenerator.fillBookingManagerWithFewOnes(bookingManager);
    VBox virtualBox = new VBox();
    BookingFx bookingFx = new BookingFx(bookingManager);
    BookingListFx bookingManagerFx = new BookingListFx(bookingManager);
    virtualBox.getChildren().addAll(bookingFx, bookingManagerFx);
    Scene scene = new Scene(virtualBox, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
    new BookingConsoleReader(bookingManager);
  }
}
