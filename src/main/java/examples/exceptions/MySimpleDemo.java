package examples.exceptions;

/**
 * Simple demo class demonstrating the usage of a self-written exception.
 */
public class MySimpleDemo {

  private void method1(boolean potentialTruth) throws NoTruthException {
    System.out.println("method1 may soon throw an exception!");
    if (!potentialTruth) {
      throw new NoTruthException("This is soooo wrong!");
    }

    System.out.println("Hey, we saw the truth!");
  }

  /**
   * Starts the demo.
   *
   * @param args No args are expected.
   */
  public static void main(String[] args) {
    MySimpleDemo mySimpleDemo = new MySimpleDemo();
    boolean myVersionOfThuth = true;
    try {
      System.out.println("Is it truth? Or not?");
      mySimpleDemo.method1(myVersionOfThuth);
      System.out.println("I told you the truth!");
    } catch (NoTruthException e) {
      System.out.println("Dammn, we got an exception: " + e.getMessage());
    }
    System.out.println("... and life goes on ...");
  }
}

