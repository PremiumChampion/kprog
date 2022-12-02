package examples.dateandtime;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class demonstrates some formatting aspects of the DateTime API.
 *
 * @author Martin Haag, Jörg Winckler
 */
public class ShowDayOfWeekMonth {
  private static final Logger logger = LoggerFactory.getLogger(ShowDayOfWeekMonth.class);

  /**
   * Demo for Usage of DayOfWeek.
   *
   * @param args arguments of the main method. No arguments expected.
   */
  public static void main(String[] args) {
    DayOfWeek dayOfWeek = DayOfWeek.MONDAY.plus(4);
    Month month = Month.AUGUST;
    Locale locale = Locale.GERMANY;

    System.out.println("The day of week in different notations:");
    System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, locale));
    System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, locale));
    System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, locale));

    System.out.println("Not the month:");
    System.out.println(month.getDisplayName(TextStyle.FULL, locale));
    System.out.println(month.getDisplayName(TextStyle.NARROW, locale));
    System.out.println(month.getDisplayName(TextStyle.SHORT, locale));

    LocalDate firstOfMay = LocalDate.of(2020, Month.MAY, 1);
    System.out.println("A fixed date: " + firstOfMay);
    LocalDate nextWed = firstOfMay.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
    System.out.println("The following Wednesday: " + nextWed);
  }
}
