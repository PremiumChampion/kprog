package prog.ex06.exercise.pizzadelivery;

/**
 * Gets thrown when the pizza order has too many toppings.
 */
public class TooManyToppingsException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TooManyToppingsException.class);

  public TooManyToppingsException() {
  }

  public TooManyToppingsException(final String message) {
    super(message);
  }

  public TooManyToppingsException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
