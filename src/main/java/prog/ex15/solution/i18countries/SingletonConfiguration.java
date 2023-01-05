package prog.ex15.solution.i18countries;

import java.beans.PropertyChangeListener;
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

  public static SingletonConfiguration getInstance() {
    return null;
  }

  @Override
  public Locale getLocale() {
    return null;
  }

  @Override
  public void setLocale(final Locale locale) {

  }

  @Override
  public ResourceBundle getTypicalBundle() {
    return null;
  }

  @Override
  public ResourceBundle getMessageBundle() {
    return null;
  }

  @Override
  public Map<Country, Locale> getCountry2LocaleMap() {
    return null;
  }

  @Override
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
  }

  @Override
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
  }
}
