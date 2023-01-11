package prog.ex15.solution.i18ncountries.gui;

import java.beans.PropertyChangeEvent;
import java.util.List;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.solution.i18ncountries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18ncountries.SingletonConfiguration;

/**
 * class MultilingualKnowledgePresenter.
 */
public class MultilingualKnowledgePresenter extends Accordion {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultilingualKnowledgePresenter.class);
  private final SingletonConfiguration configuration = SingletonConfiguration.getInstance();

  public MultilingualKnowledgePresenter() {
    configuration.addPropertyChangeListener(this::configurationChanged);
    fillAccordion();
  }

  private void configurationChanged(PropertyChangeEvent propertyChangeEvent) {
    fillAccordion();
  }

  private void fillAccordion() {
    I18nKnowledgeGenerator generator = new I18nKnowledgeGenerator();
    CountryKnowledgeContainer countryKnowledgeContainer = generator.fillContainer();

    this.getPanes().clear();
    for (Category category : Category.values()) {
      TitledPane titledPane = new TitledPane();
      titledPane.setText(
          configuration.getMessageBundle().getString("categories." + category.toString()));
      List<String> knowledgeList = countryKnowledgeContainer.getKnowledge(category);
      VBox box = new VBox();
      for (String string : knowledgeList) {
        box.getChildren().add(new Label(string));
        logger.info("Adding label " + string);
      }
      titledPane.setContent(box);
      this.getPanes().add(titledPane);
    }
  }

}
