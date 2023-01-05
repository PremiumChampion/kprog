package prog.ex15.monolingual.gui;

import java.util.List;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;

/**
 * JavaFX component presenting the content of a CountryKnowledgeContainer.
 */
public class FxKnowledgePresenter extends Accordion {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FxKnowledgePresenter.class);

  CountryKnowledgeContainer countryKnowledgeContainer;

  public FxKnowledgePresenter(final CountryKnowledgeContainer countryKnowledgeContainer) {
    this.countryKnowledgeContainer = countryKnowledgeContainer;
    fillAccordion();
  }

  private void fillAccordion() {
    this.getPanes().clear();
    for (Category category : Category.values()) {
      TitledPane titledPane = new TitledPane();
      titledPane.setText(category.toString());
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
