package prog.ex15.solution.i18countries.countries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * class SimpleCountry.
 */
public class I18NCountry implements TypicalCountry {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18NCountry.class);

  public I18NCountry(ResourceBundle bundle) {
    assertValidBundle(bundle);
    fillInformation(bundle);
  }

  private int velocityValue;
  private String velocityUnit;
  private int population;
  private String mostFamousMeal;
  private String holidayName;
  private LocalDate holidayDate;

  @Override
  public void setVelocity(int velocity, String unit) {
    this.velocityValue = velocity;
    this.velocityUnit = unit;
  }

  @Override
  public void setPopulation(int population) {
    this.population = population;
  }

  @Override
  public void setMostFamousMeal(String mostFamousMeal) {
    this.mostFamousMeal = mostFamousMeal;
  }

  @Override
  public void setMostImportantHoliday(LocalDate date, String holidayName) {
    this.holidayDate = date;
    this.holidayName = holidayName;
  }

  private void assertValidBundle(ResourceBundle bundle) throws IllegalArgumentException {
    if (bundle == null) {
      throw new IllegalArgumentException("Bundle is null");
    }
    String[] typicalCountryKeys = {
        VELOCITY,
        VELOCITY_UNIT,
        POPULATION,
        MOST_IMPORTANT_HOLIDAY_DATE,
        MOST_IMPORTANT_HOLIDAY_NAME,
        MOST_FAMOUS_MEAL
    };
    List<String> missingKeys = new ArrayList<>();
    for (String key : typicalCountryKeys) {
      if (!bundle.containsKey(key)) {
        missingKeys.add(key);
      }
    }

    if (!missingKeys.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Following keys are missing: %s", missingKeys));
    }
  }

  private void fillInformation(ResourceBundle bundle) {
    try {
      this.setMostFamousMeal((String) bundle.getObject(MOST_FAMOUS_MEAL));
      this.setPopulation((int) bundle.getObject(POPULATION));
      this.setVelocity((int) bundle.getObject(VELOCITY), (String) bundle.getObject(VELOCITY_UNIT));
      this.setMostImportantHoliday((LocalDate) bundle.getObject(MOST_IMPORTANT_HOLIDAY_DATE),
          (String) bundle.getObject(MOST_IMPORTANT_HOLIDAY_NAME));
    } catch (ClassCastException ex) {
      throw new IllegalArgumentException("Invalid bundle", ex);
    }
  }

  public int getVelocityValue() {
    return velocityValue;
  }

  public String getVelocityUnit() {
    return velocityUnit;
  }

  public int getPopulation() {
    return population;
  }

  public String getMostFamousMeal() {
    return mostFamousMeal;
  }

  public String getHolidayName() {
    return holidayName;
  }

  public LocalDate getHolidayDate() {
    return holidayDate;
  }
}
