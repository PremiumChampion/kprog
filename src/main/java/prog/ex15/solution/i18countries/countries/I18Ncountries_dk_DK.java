package prog.ex15.solution.i18countries.countries;

import java.time.LocalDate;
import java.util.ListResourceBundle;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * class i18ncountries_dk_DK.
 */
public class I18Ncountries_dk_DK extends ListResourceBundle {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18Ncountries_dk_DK.class);
  private final Object[][] contents = {
      {TypicalCountry.MOST_FAMOUS_MEAL, "knækbrød"},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE, LocalDate.of(2022, 6, 5)},
      {TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME, "Grundlovsdag"},
      {TypicalCountry.POPULATION, 5840000},
      {TypicalCountry.VELOCITY, 130},
      {TypicalCountry.VELOCITY_UNIT, "km/h"}
  };

  @Override
  protected Object[][] getContents() {
    return contents;
  }
}