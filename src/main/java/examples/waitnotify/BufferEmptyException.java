package examples.waitnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gets thrown if the buffer is empty and someone tries to extract an element.
 */
public class BufferEmptyException extends Exception {
  private static final Logger logger =
          LoggerFactory.getLogger(BufferEmptyException.class);

  public BufferEmptyException(final String s) {
    super(s);
  }
}
