package examples.javafx.mvvm;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple high-score managing service as the Model to be visualized in the MVVM concept.
 */
public class SimpleHighScoreService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleHighScoreService.class);

  private final List<HighScore> highScoreList;

  private final PropertyChangeSupport propertyChangeSupport;

  /**
   * Constructor.
   */
  public SimpleHighScoreService() {
    logger.info("Constructor called.");
    highScoreList = new ArrayList<>();
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  /**
   * Adds a new highScore to the service.
   *
   * @param highScore new HighScore to be added.
   */
  public void addHighScore(HighScore highScore) {
    logger.info("Adding Highscore " + highScore);
    int oldNumberOfEntries = getNumberOfEntries();
    highScoreList.add(highScore);
    propertyChangeSupport.firePropertyChange("numberOfEntries",
            oldNumberOfEntries, highScoreList.size());
  }

  public int getNumberOfEntries() {
    return highScoreList.size();
  }

  public List<HighScore> getHighScoreList() {
    return highScoreList;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
