package prog.ex12.exercise.datetimeapi;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.List;

/**
 * Collection of small utility methods with respect to date and time.
 */
public interface DateTimeService {

  /**
   * Returns the leap year nearest to the given year. If the distance to the given year is the
   * same for more than one solution, the earlier one shall be chosen.
   *
   * @param year year to reference to
   * @return leap year with minimum distance to the given year
   * @throws IllegalArgumentException if the year is a null reference
   */
  Year nearestLeapYear(Year year) throws IllegalArgumentException;

  /**
   * Returns the day of week of the localDate object.
   *
   * @param localDate LocalDate the day of week is requested to.
   * @return Day of week belonging to the given LocalDate
   * @throws IllegalArgumentException if the localDate object is a null reference
   */
  DayOfWeek getDayOfWeek(LocalDate localDate) throws IllegalArgumentException;

  /**
   * Calculates the difference between now and the given LocalDate.
   *
   * @param event localDate of the event
   * @return Period representing the time difference between now and then
   * @throws IllegalArgumentException if the localDate object is a null reference
   */
  Period timeBetweenNowAndThen(LocalDate event) throws IllegalArgumentException;

  /**
   * Calculates the difference between now and the referenced EventInTime object.
   *
   * @param eventId id of the EventInTime object
   * @return Period representing the time difference between now and then
   * @throws IllegalArgumentException if the eventId is not valid
   */
  Period timeBetweenNowAndThen(int eventId) throws IllegalArgumentException;

  /**
   * Adds an event to this service for future reference. The service assigns a unique id to each
   * EventInTime object.
   *
   * @param event     name of the event. The name consists only of letters, numbers and blanks. The
   *                  event must contain at minimum two readable caracters.
   * @param localDate Date of the event
   * @return id of the EventInTime object
   * @throws IllegalArgumentException if one of the parameters is a null reference or the string
   *                                  does not meet the above specification.
   */
  int addEvent(String event, LocalDate localDate) throws IllegalArgumentException;

  /**
   * Returns a list of registered events.
   *
   * @return List of EventInTime objects. If no objects are registered, the list is empty.
   */
  List<EventInTime> getEvents();

  /**
   * Removes the EventInTime object referenced by the eventId.
   *
   * @param eventId id of the object to be removed
   * @throws IllegalArgumentException if the eventId is not a valid id
   */
  void removeEvent(int eventId) throws IllegalArgumentException;

  /**
   * Loads the list of events from the given file.
   *
   * @param file file to load data from
   * @throws IOException                     if the file does not exist or cannot be read
   * @throws NoDateTimeServiceStateException if the content of the file does not meet the
   *                                         specification
   */
  void load(File file) throws IOException, NoDateTimeServiceStateException;

  /**
   * Saves the list of events to the given file.
   *
   * @param file file to be written to
   * @throws IOException if the file cannot be created and / or written.
   */
  void save(File file) throws IOException;

}
