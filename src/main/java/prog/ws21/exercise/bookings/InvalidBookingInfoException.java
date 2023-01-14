package prog.ws21.exercise.bookings;

/**
 * To be thrown when the Booking information does not meet the specification.
 */
public class InvalidBookingInfoException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(InvalidBookingInfoException.class);

  public InvalidBookingInfoException(final String message) {
    super(message);
  }

  public InvalidBookingInfoException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
