package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.HashMap;
import java.util.Map;
import prog.ex10.exercise.javafx4pizzadelivery.gui.AttributeStore;

/**
 * Simple and straight-forward singleton based implementation of an AttributeStore.
 */
public class SingletonAttributeStore implements AttributeStore {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingletonAttributeStore.class);

  private static final SingletonAttributeStore self = new SingletonAttributeStore();
  public static final String PIZZA_DELIVERY_SERVICE = "PizzaDeliveryService";
  public static final String ORDER_ID = "PizzaDeliveryOrderId";
  public static final String PIZZA_ID = "PizzaDeliveryPizzaId";

  public static SingletonAttributeStore getInstance() {
    return self;
  }

  private Map<String, Object> entries;

  private SingletonAttributeStore() {
    entries = new HashMap<>();
  }

  @Override
  public void setAttribute(final String name, final Object object) throws IllegalArgumentException {
    assertValidName(name);

    if (object == null) {
      throw new IllegalArgumentException("Value is NULL.");
    }

    this.entries.put(name, object);
  }

  @Override
  public Object getAttribute(final String name) throws IllegalArgumentException {
    assertKeyExists(name);

    return this.entries.get(name);
  }

  @Override
  public void removeAttribute(final String name) throws IllegalArgumentException {
    assertKeyExists(name);

    this.entries.remove(name);
  }

  /**
   * checks for valid name.
   *
   * @param name name.
   * @throws IllegalArgumentException illigal stuff
   */
  private void assertValidName(final String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name is NULL.");
    }
    if (name.trim().length() == 0) {
      throw new IllegalArgumentException(String.format("Invalid name: '%s'.", name));
    }
  }

  /**
   * checks for valid key.
   *
   * @param name name.
   */
  private void assertKeyExists(final String name) {
    assertValidName(name);
    if (!this.entries.containsKey(name)) {
      throw new IllegalArgumentException(String.format("Name '%s' not found.", name));
    }
  }
}
