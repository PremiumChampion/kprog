package examples.io;

/**
 * Demo showing examples of the method format.
 */
public class ExamplesForOutputUsingFormat {
  /**
   * Main method.
   *
   * @param args no args expected
   */
  public static void main(String[] args) {
    int i = 2;
    double r = Math.sqrt(i);
    System.out.println("square root of " + i + " with 'println' is " + r);
    System.out.format("square root of %d with 'format' is %f.%n", i, r);
    System.out.format("square root of %d with 'format' is %.4f.%n", i, r);
    System.out.format("square root of %e with 'format' is %s.%n", (float) i, r);
  }
}

