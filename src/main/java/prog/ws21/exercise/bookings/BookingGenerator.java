package prog.ws21.exercise.bookings;

/**
 * Simple generator to generate some test data for Booking objects.
 */
public class BookingGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BookingGenerator.class);

  public static void fillBookingManagerWithFewOnes(BookingManager manager) {
    manager.addBooking(1250, "Krimi", "Geldbörse");
    manager.addBooking(1150, "Mittagessen", "Geldbörse");
  }
}
