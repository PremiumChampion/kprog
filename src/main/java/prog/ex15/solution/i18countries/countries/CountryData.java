package prog.ex15.solution.i18countries.countries;

import java.time.LocalDate;
import java.util.ListResourceBundle;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * class i18ncountries.
 */
public class CountryData extends ListResourceBundle {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CountryData.class);

  private final Object[][] contents = {
      {TypicalCountry.MOST_FAMOUS_MEAL, "fish and chips"},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE, LocalDate.of(2022, 1, 31)},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME, "Brexit Day (Joke)"},
      {TypicalCountry.POPULATION, 66500000},
      {TypicalCountry.VELOCITY, 70},
      {TypicalCountry.VELOCITY_UNIT, "mph"}
  };

  @Override
  protected Object[][] getContents() {
    return contents;
  }
}
