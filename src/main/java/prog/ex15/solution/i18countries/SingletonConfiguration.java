package prog.ex15.solution.i18countries;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import prog.ex15.exercise.i18ncountries.Configuration;
import prog.ex15.exercise.i18ncountries.Country;

/**
 * Singleton-based implementation of the Configuration interface.
 */
public class SingletonConfiguration implements Configuration {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingletonConfiguration.class);

  private static SingletonConfiguration instance = new SingletonConfiguration();

  public static SingletonConfiguration getInstance() {
    return instance;
  }

  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private Locale locale;
  private ResourceBundle typical = null;
  private ResourceBundle message = null;

  private Map<Country, Locale> countryLocaleMap = new HashMap<>();

  private SingletonConfiguration() {
    Locale DENMARK = new Locale("dk", "DK");
    Locale ENGLAND = new Locale("en", "EN");
    Locale GERMANY = new Locale("de", "DE");
    Locale NETHERLANDS = new Locale("nl", "NL");

    this.countryLocaleMap.put(Country.DENMARK, DENMARK);
    this.countryLocaleMap.put(Country.ENGLAND, ENGLAND);
    this.countryLocaleMap.put(Country.GERMANY, GERMANY);
    this.countryLocaleMap.put(Country.NETHERLANDS, NETHERLANDS);

    setLocale(GERMANY);
  }


  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(final Locale newLocale) {
    Locale oldLocale = this.locale;
    this.locale = newLocale;

    this.message = ResourceBundle.getBundle("bundles/i18ncountries", newLocale);
    this.typical = ResourceBundle.getBundle(
        "prog.ex15.solution.i18countries.countries.I18Ncountries", newLocale);

    this.pcs.firePropertyChange("locale", oldLocale, newLocale);
  }

  @Override
  public ResourceBundle getTypicalBundle() {
    return this.typical;
  }

  @Override
  public ResourceBundle getMessageBundle() {
    return this.message;
  }

  @Override
  public Map<Country, Locale> getCountry2LocaleMap() {
    return this.countryLocaleMap;
  }

  @Override
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
  }
}
