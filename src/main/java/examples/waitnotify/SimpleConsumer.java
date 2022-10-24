package examples.waitnotify;

/**
 * The consumer gets elements from a buffer and works on them. The consumer is a Thread and has to
 * be started outside the class.
 */
public class SimpleConsumer extends Thread {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleConsumer.class);

  private final BlockingBuffer<Element> buffer;
  boolean stillWorking;

  /**
   * Constructor.
   *
   * @param buffer buffer to get the elements from
   * @param name   name of the Thread to identify the consumer in log statements
   */
  public SimpleConsumer(final BlockingBuffer<Element> buffer, String name) {
    super(name);
    this.buffer = buffer;
    stillWorking = true;
  }

  @Override
  public void run() {
    while (stillWorking) {
      Element element = null;
      try {
        element = buffer.take();
        workOnElement(element);
      } catch (InterruptedException e) {
        logger.error(e.getMessage());
        stillWorking = false;
      }
    }
  }

  private void workOnElement(final Element element) {
    // do something useful
    logger.info("consumer {} working on element {}", getName(), element.getId());
    try {
      Thread.sleep(element.getSleepingTime());
    } catch (InterruptedException e) {
      e.printStackTrace();
      stillWorking = false;
    }
  }
}
