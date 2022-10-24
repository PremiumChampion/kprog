package examples.waitnotify;

/**
 * Simple demo element. It gets configured with a sleeping time to simulate actions in the
 * consumer which will "work" on the element.
 */
public class Element {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Element.class);

  private int id;
  private static int idCounter;
  private long sleepingTime;

  public Element(long sleepingTime) {
    id = ++idCounter;
    this.sleepingTime = sleepingTime;
  }

  public int getId() {
    return id;
  }

  public long getSleepingTime() {
    return sleepingTime;
  }
}
