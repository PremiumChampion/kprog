package prog.ws21.exercise.bookings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple and straight-forward implementation of the BookingManager interface.
 */
public class SimpleBookingManager implements BookingManager {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleBookingManager.class);

  private Map<Integer, Booking> bookingMap;
  PropertyChangeSupport support;
  private static int idCounter;

  public SimpleBookingManager() {
    bookingMap = new HashMap<>();
    support = new PropertyChangeSupport(this);
  }

  @Override
  public int addBooking(int amount, String comment, String source) throws IllegalArgumentException {
    return addBooking(amount, comment, source, LocalDate.now());
  }

  @Override
  public int addBooking(int amount, String comment, String source, LocalDate date)
          throws IllegalArgumentException {
    checkIfStringContainsReadableChar(comment, "comment");
    checkIfStringContainsReadableChar(source, "source");
    List<Booking> oldList = getBookingList();
    Booking booking = new Booking(++idCounter, amount, comment, source, date);
    bookingMap.put(booking.getId(), booking);
    List<Booking> newList = getBookingList();
    support.firePropertyChange(BOOKING_LIST, oldList, newList);
    return booking.getId();

  }

  @Override
  public void removeBooking(int bookingId) throws IllegalArgumentException {
    if (!bookingMap.containsKey(bookingId)) {
      throw new IllegalArgumentException("Booking ID is unknown: " + bookingId);
    }
    List<Booking> oldList = getBookingList();
    bookingMap.remove(bookingId);
    List<Booking> newList = getBookingList();
    support.firePropertyChange(BOOKING_LIST, oldList, newList);
  }

  private void checkIfStringContainsReadableChar(final String comment, String varName) {
    if (comment == null) {
      throw new IllegalArgumentException(varName + " is null reference.");
    }

    if (comment.trim().isEmpty()) {
      throw new IllegalArgumentException(varName + " has no readable character.");
    }
  }

  @Override
  public List<Booking> getBookingList() {
    List<Booking> bookingList = new ArrayList<>(bookingMap.values());
    return bookingList;
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }
}
