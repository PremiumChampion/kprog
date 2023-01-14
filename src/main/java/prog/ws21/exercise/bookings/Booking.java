package prog.ws21.exercise.bookings;

import java.time.LocalDate;

/**
 * Represents a simple Booking for expences during vacation.
 */
public class Booking {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Booking.class);

  private int id;
  private int amount;
  private String comment;
  private LocalDate date;
  private String source;

  /**
   * Constructor.
   *
   * @param id ID of the booking. The id is usually selected by the BookingManager, otherwise it
   *           should be -1.
   * @param amount amount of the expense, in the minor part of the currency, e.g. EuroCent,
   *               USCent, ...
   * @param comment reason for the expense
   * @param source Kind of payment, e.g. cash, credit card, ...
   * @param date Day when the expense took place
   */
  public Booking(final int id, final int amount, final String comment, final String source,
                 final LocalDate date) {
    this.id = id;
    this.amount = amount;
    this.comment = comment;
    this.source = source;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(final LocalDate date) {
    this.date = date;
  }

  public String getSource() {
    return source;
  }

  public void setSource(final String source) {
    this.source = source;
  }

  @Override
  public String toString() {
    return "Booking{id=" + id + ", amount=" + amount + ", comment='" + comment + '\''
            + ", date=" + date + ", source='" + source + '\'' + '}';
  }
}
