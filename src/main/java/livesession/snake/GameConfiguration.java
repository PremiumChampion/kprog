package livesession.snake;

public class GameConfiguration {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameConfiguration.class);

  private int size;
  private int velocityInMilliSeconds;
  private int numberOfFood;

  public GameConfiguration(final int size, final int velocityInMilliSeconds,
                           final int numberOfFood) {
    this.size = size;
    this.velocityInMilliSeconds = velocityInMilliSeconds;
    this.numberOfFood = numberOfFood;
  }

  public int getSize() {
    return size;
  }

  public int getVelocityInMilliSeconds() {
    return velocityInMilliSeconds;
  }

  public int getNumberOfFood() {
    return numberOfFood;
  }
}
