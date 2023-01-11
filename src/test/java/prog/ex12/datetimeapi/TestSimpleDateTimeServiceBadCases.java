package prog.ex12.datetimeapi;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex12.exercise.datetimeapi.DateTimeService;
import prog.ex12.exercise.datetimeapi.NoDateTimeServiceStateException;
import prog.ex12.solution.datetimeapi.SimpleDateTimeService;

public class TestSimpleDateTimeServiceBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSimpleDateTimeServiceBadCases.class);
  private DateTimeService service;

  @Before
  public void setup() {
    service = new SimpleDateTimeService();
  }

  @Test(expected = RuntimeException.class) @Ignore
  public void testLoadWithNullReference() throws IOException, NoDateTimeServiceStateException {
    service.load(null);
    fail("Loading from a null reference should result in an IOException.");
  }

  @Test(expected = IOException.class) @Ignore
  public void testLoadWithNonexistingFile() throws IOException, NoDateTimeServiceStateException {
    service.load(new File("Nonexistingfilename.csv"));
    fail("Loading a nonexisting file should result in an IOException");
  }

  @Test(expected = RuntimeException.class) @Ignore
  public void testSaveWithNullReference() throws IOException {
    service.save(null);
    fail("Saving into a null reference should result in an IOException.");
  }


  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testNearestLeapYearWithNullReference() {
    service.nearestLeapYear(null);
    fail("Calling nearestLeapYear should result in an IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testGetDayOfWeekWithNullReference() {
    service.getDayOfWeek(null);
    fail("Calling getdayOfWeek should result in an IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testTimeBetweenNowAndThenWithLocalDateIsNullReference() {
    service.timeBetweenNowAndThen(null);
    fail("Calling timeBetweenNowAndThen with a null reference should result in an " +
            "IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testTimeBetweenNowAndThenWithEventIdIsInvalid() {
    service.timeBetweenNowAndThen(-345678);
    fail("Calling timeBetweenNowAndThen with an invalid id should result in an " +
            "IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithEventNameWithNullReference() {
    service.addEvent(null, LocalDate.of(2020, 12, 21));
    fail("Calling addEvent with a null reference as event name should result in an " +
            "IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithLocalDateWithNullReference() {
    service.addEvent("hugo", null);
    fail("Calling addEvent with a null reference as localDate should result in an " +
            "IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testRemoveEventWithInvalidEventId() {
    service.removeEvent(-654321);
    fail("Calling removeEvent with an invalid id should result in an IllegalArgumentException.");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithBlanckEventName() {
    service.addEvent("   ", LocalDate.of(2020, 12, 21));
    fail("addEvent with a blank event name should result in an IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithEmptyEventName() {
    service.addEvent("", LocalDate.of(2020, 12, 21));
    fail("addEvent with an empty event name should result in an IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithOnlyOneReadableCharInEventName() {
    service.addEvent("  k ", LocalDate.of(2020, 12, 21));
    fail("addEvent with only one readable char in event name should result in an " +
            "IllegalArgumentException");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddEventWithIllegalCharInEventName() {
    service.addEvent("  k§d ", LocalDate.of(2020, 12, 21));
    fail("addEvent with only one readable char in event name should result in an " +
            "IllegalArgumentException");
  }
}
