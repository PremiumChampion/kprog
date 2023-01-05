package prog.ex15.exercise.i18ncountries;

import java.beans.PropertyChangeListener;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Facade to a configuration class which simplifies I18N.
 */
public interface Configuration {

  /**
   * Returns the current Locale object.
   *
   * @return current Locale object
   */
  Locale getLocale();

  /**
   * Sets the locale to a new value. This triggers that the ResourceBundles get reloaded.
   *
   * @param locale new locale
   */
  void setLocale(Locale locale);

  /**
   * Returns the current ListResourceBundle object.
   *
   * @return current ListResourceBundle object
   */
  ResourceBundle getTypicalBundle();

  /**
   * Returns the current PropertyResourceBundle object.
   *
   * @return current PropertyResourceBundle object
   */
  ResourceBundle getMessageBundle();

  /**
   * Returns a map which associates the possible countries with their Locale objects.
   *
   * @return map with the associations of Country to Locale
   */
  Map<Country, Locale> getCountry2LocaleMap();

  /**
   * Adds a PropertyChangeListener to the configuration object.
   *
   * @param listener listener to be added
   */
  void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener from the configuration object.
   *
   * @param listener listener to be removed
   */
  void removePropertyChangeListener(PropertyChangeListener listener);
}
