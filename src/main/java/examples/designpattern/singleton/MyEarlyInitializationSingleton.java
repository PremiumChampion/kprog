package examples.designpattern.singleton;

/**
 * Singleton implementation with early initialization (thread-safe).
 */
public class MyEarlyInitializationSingleton {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MyEarlyInitializationSingleton.class);

  private static MyEarlyInitializationSingleton myself =
          new MyEarlyInitializationSingleton();

  private MyEarlyInitializationSingleton() {
    logger.info("Singleton of class {} created", MyEarlyInitializationSingleton.class.getName());
  }

  public static MyEarlyInitializationSingleton getReference() {
    return myself;
  }
}

