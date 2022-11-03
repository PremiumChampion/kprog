package examples.javafx.mvvm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

/**
 * JavaFX view to visualize a SimpleHighScoreService using the MVVM Concept.
 */
public class HighScoreView extends VBox implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HighScoreView.class);

  private final HighScoreViewModel viewModel;

  @FXML
  Button addButton;
  @FXML
  TextField name;
  @FXML
  TextField score;
  @FXML
  Label noEntriesLabel;
  @FXML
  ListView<HighScore> highScoreListView;

  /**
   * Constructor.
   *
   * @param viewModel The ViewModel which interacts with the SimpleHighScoreService.
   */
  public HighScoreView(HighScoreViewModel viewModel) {
    logger.info("Constructor called.");
    this.viewModel = viewModel;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/HighScoreView.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    logger.info("initialize called.");
    name.textProperty().bindBidirectional(viewModel.getScoreName());
    score.textProperty().bindBidirectional(viewModel.getScorePoints(), new NumberStringConverter());
    noEntriesLabel.textProperty().bind(viewModel.getNoEntries().asString());
    highScoreListView.setItems(viewModel.getObservableHighScoreList());
  }

  public void onActionAddButton(ActionEvent event) {
    logger.info("Add button clicked. Calling the ViewModel to add high-score.");
    viewModel.onActionAddHighScore();
  }
}
