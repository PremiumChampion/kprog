package prog.ex12.datetimeapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex12.exercise.datetimeapi.DateTimeService;
import prog.ex12.exercise.datetimeapi.EventInTime;
import prog.ex12.exercise.datetimeapi.NoDateTimeServiceStateException;
import prog.ex12.solution.datetimeapi.SimpleDateTimeService;

public class TestSimpleDateTimeServiceGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSimpleDateTimeServiceGoodCases.class);
  public static final String XMAS_20 = "Xmas 20";
  public static final String WORLD_WOMEN_DAY_21 = "World women Day 21";
  private DateTimeService service;

  @Before
  public void setup() {
    service = new SimpleDateTimeService();
  }

  @Test @Ignore
  public void testGetDayOfWeek() {
    assertEquals("Xmas in 2020 is a Thursday", DayOfWeek.THURSDAY,
            service.getDayOfWeek(LocalDate.of(2020, 12, 24)));
  }

  @Test  @Ignore
  public void testNearestLeapYear() {
    Year leapYear1972 = Year.of(1972);
    Year leapYear1976 = Year.of(1976);
    assertEquals(leapYear1972, service.nearestLeapYear(Year.of(1973)));
    assertEquals(leapYear1972, service.nearestLeapYear(Year.of(1974)));
    assertEquals(leapYear1976, service.nearestLeapYear(Year.of(1975)));
  }

  @Test  @Ignore
  public void testTimeBetweenNowAndThen() {
    LocalDate today = LocalDate.now();
    LocalDate in10Days = today.plusDays(10);
    Period result = service.timeBetweenNowAndThen(in10Days);
    assertEquals("Its in 10 days", 10, result.get(ChronoUnit.DAYS));
  }

  @Test @Ignore
  public void testLoadSimpleFile() throws IOException, NoDateTimeServiceStateException {
    service.load(new File("testfiles/datetimeapi-good-1.csv"));
    List<EventInTime> events = service.getEvents();
    assertNotNull("events should not be null", events);
    assertEquals("List should contain three entries", 3, events.size());
  }

  @Test @Ignore
  public void testSaveToSimpleFile() throws IOException {
    Path path = FileSystems.getDefault().getPath("testfiles", "datetimeapi-save.csv");
    try {
      Files.delete(path);
    } catch (NoSuchFileException e) {
      // OK
    }
    service.addEvent(XMAS_20, LocalDate.of(2020, 12, 24));
    service.addEvent(WORLD_WOMEN_DAY_21, LocalDate.of(2021, 3, 8));
    service.save(path.toFile());
    assertTrue(path.toFile().exists());
    Stream<String> stream = Files.lines(path);
    List<String> linesList = stream.collect(Collectors.toList());
    assertEquals("File should contain 2 lines", 2, linesList.size());
    assertTrue(linesList.get(0).contains(XMAS_20));
    assertTrue(linesList.get(0).contains("2020-12-24"));
    assertTrue(linesList.get(1).contains(WORLD_WOMEN_DAY_21));
    assertTrue(linesList.get(1).contains("2021-03-08"));

  }
}
