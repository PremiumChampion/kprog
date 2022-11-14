package examples.designpattern.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Implementing the observer part of the observer design pattern with a PropertyChangeListener.
 */
public class ProperyChangePersonObserver implements PropertyChangeListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ProperyChangePersonObserver.class);

  @Override
  public void propertyChange(final PropertyChangeEvent evt) {
    logger.info("Observable {} changed with property {}. Old value = {}, new value = {}.",
            evt.getSource(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
  }
}

