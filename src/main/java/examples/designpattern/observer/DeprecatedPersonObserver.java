package examples.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Demonstrates the usage of Observer.
 */
public class DeprecatedPersonObserver implements Observer {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DeprecatedPersonObserver.class);

  @Override
  public void update(final Observable o, final Object arg) {
    logger.info("Observer called from Observable {} with value {}", o, arg);
  }
}


