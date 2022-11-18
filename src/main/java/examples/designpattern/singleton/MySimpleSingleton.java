package examples.designpattern.singleton;

/**
 * Very simple singleton implementation (NOT thread-safe).
 */
public class MySimpleSingleton {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MySimpleSingleton.class);

  private static MySimpleSingleton myself;

  private MySimpleSingleton() {
  }

  /**
   * Returns reference to the one and only one existing object.
   *
   * @return reference to the singleton
   */
  public static MySimpleSingleton getReference() {
    // Lazy initialization
    if (myself == null) {
      logger.info("Oh, this is the first call, I create an object.");
      myself = new MySimpleSingleton();
    }
    return myself;
  }
}



