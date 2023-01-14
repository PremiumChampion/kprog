package prog.ws21.exercise.bookings;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

/**
 * Creates Booking objects based on a String.
 */
public class BookingParser {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BookingParser.class);

  /**
   * Parses a command line for details of a booking. The given line must fit the specification
   * given in the method getCliFormatExplanation
   *
   * @param line line to be parsed
   * @return new Booking object
   * @throws InvalidBookingInfoException if the line does not specify the booking correctly
   */
  public static Booking parseBookingFromCli(String line) throws InvalidBookingInfoException {
    StringTokenizer st = new StringTokenizer(line, ";");
    if (st.countTokens() != 4) {
      throw new InvalidBookingInfoException(
              "The line does not have the correct number of elements.");
    }
    String comment = st.nextToken().trim();
    String amountAsString = st.nextToken().trim();
    String source = st.nextToken().trim();
    String dateAsString = st.nextToken().trim();
    logger.info("Your input: {} | {} | {} | {}", comment, amountAsString, source, dateAsString);

    try {
      int amount = Integer.parseInt(amountAsString);
      LocalDate date = LocalDate.parse(dateAsString);
      Booking booking = new Booking(-1, amount, comment, source, date);
      return booking;
    } catch (NumberFormatException e) {
      throw new InvalidBookingInfoException(
              "Given amount is not an integer: " + amountAsString, e);
    } catch (DateTimeParseException e) {
      throw new InvalidBookingInfoException(
              "Given date is not in Format yyyy-MM-dd: " + dateAsString, e);

    }
  }

  /**
   * Returns the explanation of the input format to be used when the booking is entered via the
   * console.
   *
   * @return  String explaining the expected format
   */
  public static String getCliFormatExplanation() {
    return "Please enter your booking as"
            + " <reason>;<amount in cent>;<source>;<date as yyyy-MM-dd>";
  }
}
