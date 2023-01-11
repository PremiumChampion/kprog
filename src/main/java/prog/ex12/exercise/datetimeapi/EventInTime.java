package prog.ex12.exercise.datetimeapi;

import java.time.LocalDate;

/**
 * EventInTime represents an event at a certain date in past or future.
 */
public class EventInTime {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(EventInTime.class);

  private final int eventId;
  private final String name;
  private final LocalDate localDate;

  /**
   * Constructor.
   *
   * @param eventId   unique Id for the new EventInTime object
   * @param name      name of the event
   * @param localDate date of the event
   */
  public EventInTime(final int eventId, final String name, final LocalDate localDate) {
    this.eventId = eventId;
    this.name = name;
    this.localDate = localDate;
  }

  public int getEventId() {
    return eventId;
  }

  public String getName() {
    return name;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }
}
