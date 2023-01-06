package prog.ex15.solution.i18countries.countries;

import java.time.LocalDate;
import java.util.ListResourceBundle;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * class i18ncountries_de_DE.
 */
public class I18Ncountries_de_DE extends ListResourceBundle {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18Ncountries_de_DE.class);
  private final Object[][] contents = {
      {TypicalCountry.MOST_FAMOUS_MEAL, "Eisbein mit Sauerkraut"},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE, LocalDate.of(2022, 10, 3)},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME, "Tag der Deutschen Einheit"},
      {TypicalCountry.POPULATION, 83200000},
      {TypicalCountry.VELOCITY, 130},
      {TypicalCountry.VELOCITY_UNIT, "km/h"}
  };

  @Override
  protected Object[][] getContents() {
    return contents;
  }
}
