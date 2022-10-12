package livesession.snake;

/**
 * Signals that the GameConfiguration contains values not appropriate for the game.
 */
public class IllegalConfigurationException extends Exception {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(IllegalConfigurationException.class);

  public IllegalConfigurationException(final String message) {
    super(message);
  }
}
