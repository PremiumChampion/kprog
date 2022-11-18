package examples.designpattern.singleton;

/**
 * Simple singleton implementation using synchronized (thread-safe).
 */
public class MySynchronizedSimpleSingleton {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MySynchronizedSimpleSingleton.class);

  private static MySynchronizedSimpleSingleton myself;

  private MySynchronizedSimpleSingleton() {
  }

  /**
   * Returns reference to the one and only one existing object.
   *
   * @return reference to the singleton
   */
  public static synchronized MySynchronizedSimpleSingleton getReference() {
    // Lazy initialization
    if (myself == null) {
      logger.info("Oh, this is the first call, I create an object.");
      myself = new MySynchronizedSimpleSingleton();
    }
    return myself;
  }
}



