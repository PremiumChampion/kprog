package prog.ex15.solution.i18ncountries;

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
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * Simple, straight-forward implementation of the KnowledgeGenerator interface for multiple
 * countries.
 */
public class I18nKnowledgeGenerator implements KnowledgeGenerator {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      I18nKnowledgeGenerator.class);

  @Override
  public CountryKnowledgeContainer fillContainer() {
    final ResourceBundle messageBundle = SingletonConfiguration.getInstance().getMessageBundle();
    assertValidBundle(messageBundle);
    final Locale locale = SingletonConfiguration.getInstance().getLocale();
    final Country countryData = new Country(locale);
    final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        .withLocale(locale);

    logger.info("locale {}", locale);

    CountryKnowledgeContainer container = new CountryKnowledgeContainer();
    String foodText = getFoodText(messageBundle, countryData);
    container.addKnowledge(Category.FOOD, foodText);
    String holidayText = getHolidayText(messageBundle, countryData, dateTimeFormatter);
    container.addKnowledge(Category.HOLIDAYS, holidayText);
    String trafficText = getTrafficText(messageBundle, countryData, numberFormat);
    container.addKnowledge(Category.TRAFFIC, trafficText);
    String statisticsText = getStatisticsText(messageBundle, countryData, numberFormat);
    container.addKnowledge(Category.STATISTICS, statisticsText);

    return container;
  }

  /**
   * comment.
   *
   * @param messageBundle comment.
   * @param countryData   comment.
   * @param numberFormat  comment.
   * @return comment.
   */
  private String getStatisticsText(ResourceBundle messageBundle, Country countryData,
      NumberFormat numberFormat) {
    String statisticsText = MessageFormat.format(messageBundle.getString("statistics.population"),
        numberFormat.format(countryData.getPopulation()));
    logger.info("statisticsText: {}", statisticsText);
    return statisticsText;
  }

  /**
   * comment.
   *
   * @param messageBundle comment.
   * @param countryData   comment.
   * @param numberFormat  comment.
   * @return comment.
   */
  private String getTrafficText(ResourceBundle messageBundle, Country countryData,
      NumberFormat numberFormat) {
    String trafficText = MessageFormat.format(
        messageBundle.getString("traffic.maximum.speed.highways"),
        numberFormat.format(countryData.getVelocityValue()), countryData.getVelocityUnit());
    logger.info("trafficText: {}", trafficText);
    return trafficText;
  }

  /**
   * comment.
   *
   * @param messageBundle     comment.
   * @param countryData       comment.
   * @param dateTimeFormatter comment.
   * @return comment.
   */
  private String getHolidayText(ResourceBundle messageBundle, Country countryData,
      DateTimeFormatter dateTimeFormatter) {
    String holidayText = MessageFormat.format(
        messageBundle.getString("holiday.most.important.holiday"), countryData.getHolidayName(),
        dateTimeFormatter.format(countryData.getHolidayDate()));
    logger.info("holidayText: {}", holidayText);
    return holidayText;
  }

  /**
   * comment.
   *
   * @param messageBundle comment.
   * @param countryData   comment.
   * @return comment.
   */
  private String getFoodText(ResourceBundle messageBundle, Country countryData) {
    String foodText = MessageFormat.format(messageBundle.getString("food.most.prominent.food"),
        countryData.getMostFamousMeal());
    logger.info("foodText: {}", foodText);
    return foodText;
  }

  /**
   * comment.
   *
   * @param bundle comment.
   * @throws IllegalArgumentException comment.
   */
  private void assertValidBundle(ResourceBundle bundle) throws IllegalArgumentException {
    if (bundle == null) {
      throw new IllegalArgumentException("Bundle is null");
    }
    String[] typicalCountryKeys = {"categories.FOOD", "categories.HOLIDAYS",
        "categories.STATISTICS", "categories.TRAFFIC", "country.DENMARK", "country.ENGLAND",
        "country.GERMANY", "country.NETHERLANDS", "food.most.prominent.food",
        "holiday.most.important.holiday", "statistics.population",
        "traffic.maximum.speed.highways"};
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
