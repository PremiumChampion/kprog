package prog.bonus.exercise.checklistservice;

/**
 * To be thrown when an entity with the same name already exists.
 */
public class DuplicateNameException extends Exception {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuplicateNameException.class);

  public DuplicateNameException(final String message) {
    super(message);
  }
}
