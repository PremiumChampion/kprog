package environment.testprograms;

import prog.ex05.solution.masterworker.SimpleMaster;

/**
 * Simple test program using the simple logger facade.
 */
public class HelloWorld {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      HelloWorld.class);

  public static void main(String[] args) throws InterruptedException {
    logger.info("Hello, brave new World!");
  }
}
