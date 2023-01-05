package prog.bonus.exercise.checklistservice;

/**
 * Thrown when a user tries to access a list that he is not authorized to access.
 */
public class NotAuthorizedException extends Exception {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(NotAuthorizedException.class);

  public NotAuthorizedException(final String message) {
    super(message);
  }
}
