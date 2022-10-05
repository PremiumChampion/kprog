package examples.exceptions;

/**
 * Get thrown, if there is no truth.
 */
public class NoTruthException extends Exception {

  public NoTruthException(final String message) {
    super(message);
  }
}

