package prog.ex15.solution.i18countries.gui;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import prog.ex15.exercise.i18ncountries.Country;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.solution.i18countries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18countries.SingletonConfiguration;
import prog.ex15.solution.i18countries.countries.I18NCountry;

/**
 * class I18CountriesRoot.
 */
public class I18CountriesRoot extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18CountriesRoot.class);
  private final SingletonConfiguration configuration = SingletonConfiguration.getInstance();
  MultilingualKnowledgePresenter presenter;
  ChoiceBox<String> countrySelector;
  private EventHandler<ActionEvent> countrySelectorOnAction = this::countryChanged;

  public I18CountriesRoot() {
    countrySelector = new ChoiceBox<>();
    updateCountrySelector();
    countrySelector.setOnAction(countrySelectorOnAction);
    this.getChildren().add(countrySelector);

    presenter = new MultilingualKnowledgePresenter();
    this.getChildren().add(presenter);

    configuration.addPropertyChangeListener(this::configurationChanged);
  }

  private void configurationChanged(PropertyChangeEvent propertyChangeEvent) {
    updateCountrySelector();
  }

  private List<String> getCurrentCountryTranslation() {
    return Arrays.stream(Country.values())
        .map(c -> "country." + c)
        .map(c -> configuration.getMessageBundle().getString(c))
        .collect(Collectors.toList());
  }

  private void countryChanged(ActionEvent actionEvent) {
    Locale locale = configuration.getCountry2LocaleMap()
        .get(Country.values()[countrySelector.getSelectionModel().getSelectedIndex()]);
    configuration.setLocale(locale);
  }

  private void updateCountrySelector() {
    countrySelector.getItems().clear();
    countrySelector.getItems().addAll(getCurrentCountryTranslation());
    configuration.getCountry2LocaleMap().forEach((key, value) -> {
      if (value.equals(configuration.getLocale())) {
        countrySelector.getSelectionModel().select(key.ordinal());
      }
    });
  }
}
