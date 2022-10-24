package examples.waitnotify;

/**
 * Demo program to show the consumer synchronisation in the producer-consumer scenario.
 */
public class SimpleProducerConsumerLauncher {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleProducerConsumerLauncher.class);

  private static final int NUMBER_CONSUMERS = 5;

  /**
   * Main.
   *
   * @param args no args are expected
   */
  public static void main(String[] args) {
    BlockingBuffer<Element> buffer = new BlockingBuffer<>(50);
    for (int loop = 0; loop < NUMBER_CONSUMERS; loop++) {
      SimpleConsumer consumer = new SimpleConsumer(buffer, "consumer-" + loop);
      consumer.start();
    }
    SimpleProducer producer = new SimpleProducer(buffer);
    producer.start();

  }
}
