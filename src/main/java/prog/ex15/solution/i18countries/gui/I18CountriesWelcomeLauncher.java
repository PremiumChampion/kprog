package prog.ex15.solution.i18countries.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class I18CountriesWelcomeLauncher.
 */
public class I18CountriesWelcomeLauncher extends Application {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18CountriesWelcomeLauncher.class);

  @Override
  public void start(Stage stage) throws Exception {
    I18CountriesRoot root = new I18CountriesRoot();
    stage.setScene(new Scene(root, 400, 300));
    stage.show();
  }
}
