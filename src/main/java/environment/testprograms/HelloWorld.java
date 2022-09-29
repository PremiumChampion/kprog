package environment.testprograms;

/**
 * Simple test program using the simple logger facade.
 */
public class HelloWorld {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HelloWorld.class);

  public static void main(String[] args) {
    logger.info("Hello, brave new World!");
  }
}
