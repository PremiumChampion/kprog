package prog.ex15.exercise.i18ncountries;

import java.time.LocalDate;

/**
 * Interface to make the creation of a ListResourceBundle more reliable.
 */
public interface TypicalCountry {

  String VELOCITY = "velocity";
  String VELOCITY_UNIT = "velocity-unit";
  String POPULATION = "population";
  String MOST_IMPORTANT_HOLIDAY_DATE = "most-important-holiday-date";
  String MOST_IMPORTANT_HOLIDAY_NAME = "most-important-holiday-name";
  String MOST_FAMOUS_MEAL = "most-famous-meal";

  /**
   * Setter for the maximum velocity on streets.
   *
   * @param velocity maximum allowed speed. If there is no maximum, the recommended velocity should
   *                 be used.
   * @param unit     unit for the velocity, e.g. "km/h" in Europe, "mph" in USA
   */
  void setVelocity(int velocity, String unit);

  /**
   * Number of people living in this country.
   *
   * @param population number of people
   */
  void setPopulation(int population);

  /**
   * Most famous meal the country is known for.
   *
   * @param mostFamousMeal Name of the most famous meal
   */
  void setMostFamousMeal(String mostFamousMeal);

  /**
   * Most important holiday in this country.
   *
   * @param date        date of the current year the holiday takes place
   * @param holidayName Name of the holiday
   */
  void setMostImportantHoliday(LocalDate date, String holidayName);
}
