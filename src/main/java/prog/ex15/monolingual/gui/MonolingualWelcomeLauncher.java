package prog.ex15.monolingual.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.monolingual.StupidKnowledgeGenerator;

/**
 * Main to launch the WelcomeToMyCountry content in a separate application.
 */
public class MonolingualWelcomeLauncher extends Application {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MonolingualWelcomeLauncher.class);

  @Override
  public void start(final Stage stage) throws Exception {
    CountryKnowledgeContainer container = new StupidKnowledgeGenerator().fillContainer();
    FxKnowledgePresenter presenter = new FxKnowledgePresenter(container);
    stage.setScene(new Scene(presenter, 400, 300));
    stage.show();
  }
}
