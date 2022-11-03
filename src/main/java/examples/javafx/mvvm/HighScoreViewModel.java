package examples.javafx.mvvm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * The ViewModel of the MVVM concept to visualize a SimpleHighScoreService.
 */
public class HighScoreViewModel implements PropertyChangeListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HighScoreViewModel.class);

  private final SimpleHighScoreService service;
  private final SimpleStringProperty scoreName;
  private final SimpleIntegerProperty scorePoints;
  private final SimpleIntegerProperty noEntries;
  private final ObservableList<HighScore> observableHighScoreList;

  /**
   * Constructor.
   *
   * @param highScoreService the SimpleHighScoreService which represents the Model part of the MVVM.
   */
  public HighScoreViewModel(final SimpleHighScoreService highScoreService) {
    logger.info("Constructor called.");
    this.service = highScoreService;
    this.service.addPropertyChangeListener(this);
    observableHighScoreList = FXCollections.observableList(new ArrayList<HighScore>());
    scoreName = new SimpleStringProperty("hugo");
    scorePoints = new SimpleIntegerProperty(0);
    noEntries = new SimpleIntegerProperty(0);
    updateModelData();
  }

  public StringProperty getScoreName() {
    return scoreName;
  }

  public IntegerProperty getScorePoints() {
    return scorePoints;
  }

  public IntegerProperty getNoEntries() {
    return noEntries;
  }

  public SimpleIntegerProperty noEntriesProperty() {
    return noEntries;
  }

  public ObservableList<HighScore> getObservableHighScoreList() {
    return observableHighScoreList;
  }

  /**
   * Adds  high-score to the SimpleHighScoreService.
   */
  public void onActionAddHighScore() {
    logger.info("onActionAddHighScore");
    // get user input from StringProperty and IntegerProperty
    HighScore highScore = new HighScore(scoreName.getValue(), scorePoints.intValue());
    // add high-score to service (model)
    service.addHighScore(highScore);
  }

  @Override
  public void propertyChange(final PropertyChangeEvent evt) {
    logger.info("propertyChange: " + evt.getPropertyName());
    updateModelData();
    logger.info("observableList has elements: " + observableHighScoreList.size());
  }

  private void updateModelData() {
    List<HighScore> highScoreList = service.getHighScoreList();
    observableHighScoreList.clear();
    observableHighScoreList.addAll(highScoreList);
    noEntries.setValue(highScoreList.size());
  }
}


