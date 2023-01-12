package prog.ex12.solution.datetimeapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import prog.ex12.exercise.datetimeapi.DateTimeService;
import prog.ex12.exercise.datetimeapi.EventInTime;
import prog.ex12.exercise.datetimeapi.NoDateTimeServiceStateException;

/**
 * Simple and straight-forward implementation of the DateTimeService interface.
 */
public class SimpleDateTimeService implements DateTimeService {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SimpleDateTimeService.class);
  private Map<Integer, EventInTime> events = new HashMap<>();
  private static final Random idGenerator = new Random(420);

  @Override
  public Year nearestLeapYear(final Year year) throws IllegalArgumentException {
    if (year == null) {
      throw new IllegalArgumentException("year is null");
    }
    Year positive = year;
    Year negative = year;
    Year result;

    while (true) {
      if (negative.isLeap()) {
        result = negative;
        break;
      }
      if (positive.isLeap()) {
        result = positive;
        break;
      }
      positive = positive.plusYears(1);
      negative = negative.minusYears(1);
    }

    return result;
  }

  @Override
  public DayOfWeek getDayOfWeek(final LocalDate localDate) throws IllegalArgumentException {
    if (localDate == null) {
      throw new IllegalArgumentException("localDate is null");
    }
    return localDate.getDayOfWeek();
  }

  @Override
  public Period timeBetweenNowAndThen(final LocalDate event) throws IllegalArgumentException {
    if (event == null) {
      throw new IllegalArgumentException("event is null");
    }
    Period timeBetween;
    if (event.isBefore(LocalDate.now())) {
      timeBetween = Period.between(event, LocalDate.now());
    } else {
      timeBetween = Period.between(LocalDate.now(), event);
    }
    return timeBetween;
  }

  @Override
  public Period timeBetweenNowAndThen(final int eventId) throws IllegalArgumentException {
    assertValidEventId(eventId);
    EventInTime event = events.get(eventId);

    return timeBetweenNowAndThen(event.getLocalDate());
  }

  private void assertValidEventId(int eventId) throws IllegalArgumentException {
    if (!events.containsKey(eventId)) {
      throw new IllegalArgumentException(String.format("Event with id %d not found", eventId));
    }
  }

  @Override
  public int addEvent(final String event, final LocalDate localDate)
      throws IllegalArgumentException {
    assertValidEventName(event);
    if (localDate == null) {
      throw new IllegalArgumentException("Locale date is null");
    }
    int id = idGenerator.nextInt();
    while (events.containsKey(id)) {
      id = idGenerator.nextInt();
    }
    EventInTime eventInTime = new EventInTime(id, event, localDate);
    events.put(id, eventInTime);
    return id;
  }

  @Override
  public List<EventInTime> getEvents() {
    return new ArrayList<>(events.values());
  }

  @Override
  public void removeEvent(final int eventId) throws IllegalArgumentException {
    assertValidEventId(eventId);
    events.remove(eventId);
  }

  @Override
  public void load(final File file) throws IOException, NoDateTimeServiceStateException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      for (String line : bufferedReader.lines().toArray(String[]::new)) {
        String eventMetadata = line.trim();
        String[] eventTokens = eventMetadata.split(";");

        if (eventTokens.length != 3) {
          throw new NoDateTimeServiceStateException(
              String.format("Unexpected amount of tokens for entry '%s'", eventMetadata));
        }

        int eventId = parseEventId(eventTokens);
        String eventName = parseEventName(eventTokens);
        LocalDate eventDate = parseEventDate(eventTokens);

        events.put(eventId, new EventInTime(eventId, eventName, eventDate));
      }
    }
  }

  private int parseEventId(String[] eventMetadata) throws NoDateTimeServiceStateException {
    try {
      int eventId = Integer.parseInt(eventMetadata[0]);
      if (events.containsKey(eventId)) {
        throw new NoDateTimeServiceStateException(
            String.format("Event with id '%d' already exists", eventId));
      }
      return eventId;
    } catch (NumberFormatException numberFormatException) {
      throw new NoDateTimeServiceStateException(
          String.format("Event id '%s' invalid", eventMetadata[0]), numberFormatException);
    }
  }

  private void assertValidEventName(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException(String.format("Event description '%s' invalid", name));
    }
    name = name.replaceAll("\\s", "");

    int validCount = 0;

    for (Character c : name.toCharArray()) {
      if (!Character.isLetterOrDigit(c)) {
        throw new IllegalArgumentException("Illegal character...");
      }
      validCount++;
    }

    if (validCount < 2) {
      throw new IllegalArgumentException("Illegal character count...");
    }
  }

  private String parseEventName(String[] eventMetadata) throws NoDateTimeServiceStateException {
    try {
      String eventName = eventMetadata[1];
      assertValidEventName(eventName);
      return eventName;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new NoDateTimeServiceStateException(
          String.format("Event name '%s' invalid", eventMetadata[1]), illegalArgumentException);
    }
  }

  private LocalDate parseEventDate(String[] eventMetadata) throws NoDateTimeServiceStateException {
    try {
      String eventDate = eventMetadata[2]; // YYYY-MM-DD
      return LocalDate.parse(eventDate, DateTimeFormatter.ISO_DATE);
    } catch (DateTimeParseException dateTimeParseException) {
      throw new NoDateTimeServiceStateException(
          String.format("Event date '%s' invalid", eventMetadata[2]), dateTimeParseException);
    }
  }

  @Override
  public void save(final File file) throws IOException {
    try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
      List<String> serialisedEvents = events.values().stream().map(this::serializeEvent)
          .collect(Collectors.toList());
      String eventsString = String.join("\n", serialisedEvents);
      printWriter.println(eventsString);
      printWriter.flush();
    }
  }

  private String serializeEvent(EventInTime eventInTime) {
    return String.format("%d;%s;%s", eventInTime.getEventId(), eventInTime.getName(),
        eventInTime.getLocalDate().format(DateTimeFormatter.ISO_DATE));
  }
}
