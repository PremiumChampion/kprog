package prog.ex15.solution.i18countries.countries;

import java.time.LocalDate;
import java.util.ListResourceBundle;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * class i18ncountries_nl_NL.
 */
public class CountryData_nl_NL extends ListResourceBundle {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CountryData_nl_NL.class);
  private final Object[][] contents = {
      {TypicalCountry.MOST_FAMOUS_MEAL, "Pannekoken"},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE, LocalDate.of(2022, 4, 27)},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME, "Koningsdag"},
      {TypicalCountry.POPULATION, 17500000},
      {TypicalCountry.VELOCITY, 120},
      {TypicalCountry.VELOCITY_UNIT, "km/h"}
  };

  @Override
  protected Object[][] getContents() {
    return contents;
  }
}