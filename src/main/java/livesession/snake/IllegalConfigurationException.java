package livesession.snake;

public class IllegalConfigurationException extends Exception {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(IllegalConfigurationException.class);

  public IllegalConfigurationException(final String message) {
    super(message);
  }
}
