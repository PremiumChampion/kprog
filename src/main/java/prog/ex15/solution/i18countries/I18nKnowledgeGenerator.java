package prog.ex15.solution.i18countries;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.exercise.i18ncountries.KnowledgeGenerator;
import prog.ex15.solution.i18countries.countries.Country;

/**
 * Simple, straight-forward implementation of the KnowledgeGenerator interface for multiple
 * countries.
 */
public class I18nKnowledgeGenerator implements KnowledgeGenerator {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18nKnowledgeGenerator.class);
  private final Country countryData;
  private final ResourceBundle texts;
  private final Locale locale;

  /**
   * constructor.
   *
   * @param countryData data.
   * @param texts       data.
   */
  public I18nKnowledgeGenerator(Country countryData, ResourceBundle texts) {
    this.locale = texts.getLocale();
    assertValidBundle(texts);
    this.countryData = countryData;
    this.texts = texts;
    this.numberFormat = NumberFormat.getNumberInstance(locale);
    this.dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
  }

  private final NumberFormat numberFormat;
  private final DateTimeFormatter dateTimeFormatter;


  private String printMessage(final String bundleKey, final int noPeople, final LocalDate date) {
    return MessageFormat.format(
        texts.getString(bundleKey),
        numberFormat.format(noPeople),
        dateTimeFormatter.format(date));
  }

  @Override
  public CountryKnowledgeContainer fillContainer() {
    CountryKnowledgeContainer container = new CountryKnowledgeContainer();
    container.addKnowledge(Category.FOOD,
        MessageFormat.format(texts.getString("food.most.prominent.food"),
            this.countryData.getMostFamousMeal()));
    container.addKnowledge(Category.HOLIDAYS,
        MessageFormat.format(texts.getString("holiday.most.important.holiday"),
            this.countryData.getHolidayName(),
            dateTimeFormatter.format(this.countryData.getHolidayDate())));
    container.addKnowledge(Category.TRAFFIC,
        MessageFormat.format(texts.getString("traffic.maximum.speed.highways"),
            numberFormat.format(this.countryData.getVelocityValue()),
            this.countryData.getVelocityUnit()));
    container.addKnowledge(Category.STATISTICS,
        MessageFormat.format(texts.getString("statistics.population"),
            numberFormat.format(this.countryData.getPopulation())));
    return container;
  }

  private void assertValidBundle(ResourceBundle bundle) throws IllegalArgumentException {
    if (bundle == null) {
      throw new IllegalArgumentException("Bundle is null");
    }
    String[] typicalCountryKeys = {
        "categories.FOOD",
        "categories.HOLIDAYS",
        "categories.STATISTICS",
        "categories.TRAFFIC",
        "country.DENMARK",
        "country.ENGLAND",
        "country.GERMANY",
        "country.NETHERLANDS",
        "food.most.prominent.food",
        "holiday.most.important.holiday",
        "statistics.population",
        "traffic.maximum.speed.highways"
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
}
