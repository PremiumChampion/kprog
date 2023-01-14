package prog.ws21.exercise.bookings;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

/**
 * The BookingManager cumulates booking information, e.g. during vacation.
 */
public interface BookingManager {
  String BOOKING_LIST = "bookingList";

  /**
   * Adds a booking.
   *
   * @param amount  amount of the expense, @see {Booking}
   * @param comment reason for the expense, , @see {Booking}
   * @param source  way of payment, @see {Booking}
   *
   * @return id of the new Booking object within the BookingManager
   * @throws IllegalArgumentException if comment and source a non-readable Strings
   */
  int addBooking(int amount, String comment, String source) throws IllegalArgumentException;

  /**
   * Adds a booking.
   *
   * @param amount  amount of the expense, @see {Booking}
   * @param comment reason for the expense, , @see {Booking}
   * @param source  way of payment, @see {Booking}
   * @param date    date of the expense
   *
   * @return id of the new Booking object within the BookingManager
   * @throws IllegalArgumentException if comment and source a non-readable Strings
   */
  int addBooking(int amount, String comment, String source, LocalDate date)
          throws IllegalArgumentException;

  /**
   * Removes a booking.
   *
   * @param bookingId id of the booking to be removed
   * @throws IllegalArgumentException if the id of the booking does not exist
   */
  void removeBooking(int bookingId) throws IllegalArgumentException;

  /**
   * Returns the list of bookings.
   *
   * @return list of bookings added to the BookingManager
   */
  List<Booking> getBookingList();

  /**
   * adds a PropertyChangeListener to the BookingManager.
   *
   * @param listener PropertyChangeListener to be added
   */
  void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener.
   *
   * @param listener Listener to be removed
   */
  void removePropertyChangeListener(PropertyChangeListener listener);
}
