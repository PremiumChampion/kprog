package examples.threads.simplequeue;

public class Food {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Food.class);

  private String name;

  public Food(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
