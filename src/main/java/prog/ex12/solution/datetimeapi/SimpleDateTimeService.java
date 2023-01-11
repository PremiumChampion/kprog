package prog.ex12.solution.datetimeapi;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.List;
import prog.ex12.exercise.datetimeapi.DateTimeService;
import prog.ex12.exercise.datetimeapi.EventInTime;
import prog.ex12.exercise.datetimeapi.NoDateTimeServiceStateException;

/**
 * Simple and straight-forward implementation of the DateTimeService interface.
 */
public class SimpleDateTimeService implements DateTimeService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleDateTimeService.class);

  @Override
  public Year nearestLeapYear(final Year year) throws IllegalArgumentException {
    return null;
  }

  @Override
  public DayOfWeek getDayOfWeek(final LocalDate localDate) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Period timeBetweenNowAndThen(final LocalDate event) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Period timeBetweenNowAndThen(final int eventId) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int addEvent(final String event, final LocalDate localDate)
          throws IllegalArgumentException {
    return 0;
  }

  @Override
  public List<EventInTime> getEvents() {
    return null;
  }

  @Override
  public void removeEvent(final int eventId) throws IllegalArgumentException {

  }

  @Override
  public void load(final File file) throws IOException, NoDateTimeServiceStateException {

  }

  @Override
  public void save(final File file) throws IOException {

  }
}
