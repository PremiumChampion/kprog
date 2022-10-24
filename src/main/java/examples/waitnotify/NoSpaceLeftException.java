package examples.waitnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gets thrown if the buffer is full and someone tries to insert another element.
 */
public class NoSpaceLeftException extends Exception {
  private static final Logger logger =
          LoggerFactory.getLogger(NoSpaceLeftException.class);

  public NoSpaceLeftException(final String s) {
    super(s);
  }
}
