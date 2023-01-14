package prog.ws21.solution.bookings.gui;

import java.util.Scanner;
import prog.ws21.exercise.bookings.Booking;
import prog.ws21.exercise.bookings.BookingManager;
import prog.ws21.exercise.bookings.BookingParser;
import prog.ws21.exercise.bookings.InvalidBookingInfoException;

/**
 * class BookingConsoleReader.
 */
public class BookingConsoleReader extends Thread {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BookingConsoleReader.class);
  private final BookingManager bookingManager;

  public BookingConsoleReader(BookingManager bookingManager) {
    this.bookingManager = bookingManager;
    this.setDaemon(true);
    this.start();
  }

  @Override
  public void run() {
    super.run();
    Scanner s = new Scanner(System.in);
    while (true) {
      try {
        System.out.println(BookingParser.getCliFormatExplanation());
        Booking b = BookingParser.parseBookingFromCli(s.nextLine());
        bookingManager.addBooking(b.getAmount(), b.getComment(), b.getSource(), b.getDate());
      } catch (InvalidBookingInfoException e) {
        System.out.println("Please enter the booking in the correct format.");
      }
    }
  }
}
