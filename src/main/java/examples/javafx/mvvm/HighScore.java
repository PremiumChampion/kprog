package examples.javafx.mvvm;

import java.time.LocalDateTime;

/**
 * Represents a simple high-score containing a name and an int value.
 */
public class HighScore {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HighScore.class);

  private String name;
  private int score;
  private LocalDateTime created;

  /**
   * Constructor.
   *
   * @param name name of the person obtaining the high-score.
   * @param score number of points the person obtained.
   */
  public HighScore(final String name, final int score) {
    this.name = name;
    this.score = score;
    created = LocalDateTime.now();
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  @Override
  public String toString() {
    return "HighScore{"
            + "name='" + name + '\''
            + ", score=" + score
            + ", created=" + created + '}';
  }
}
