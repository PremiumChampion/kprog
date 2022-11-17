package prog.ex10.solution.javafx4pizzadelivery.gui;

import prog.ex10.exercise.javafx4pizzadelivery.gui.AttributeStore;

/**
 * Simple and straight-forward singleton based implementation of an AttributeStore.
 */
public class SingletonAttributeStore implements AttributeStore {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingletonAttributeStore.class);

  private static final SingletonAttributeStore self = new SingletonAttributeStore();

  public static SingletonAttributeStore getInstance() {
    return self;
  }

  private SingletonAttributeStore() {
  }

  @Override
  public void setAttribute(final String name, final Object object) throws IllegalArgumentException {

  }

  @Override
  public Object getAttribute(final String name) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void removeAttribute(final String name) {

  }
}
