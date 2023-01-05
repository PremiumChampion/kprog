package prog.bonus.exercise.checklistservice;

/**
 * Thrown when a tag is in use.
 */
public class TagInUseException extends Exception {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TagInUseException.class);

  public TagInUseException(final String message) {
    super(message);
  }
}
