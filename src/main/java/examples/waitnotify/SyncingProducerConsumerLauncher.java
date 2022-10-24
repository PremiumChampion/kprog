package examples.waitnotify;

/**
 * Demo program to show the consumer synchronisation in the producer-consumer scenario.
 */
public class SyncingProducerConsumerLauncher {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SyncingProducerConsumerLauncher.class);

  private static final int NUMBER_CONSUMERS = 5;

  /**
   * Main.
   *
   * @param args no args are expected
   */
  public static void main(String[] args) {
    Buffer<Element> buffer = new Buffer<>(50);
    for (int loop = 0; loop < NUMBER_CONSUMERS; loop++) {
      SyncingConsumer consumer = new SyncingConsumer(buffer, "consumer-" + loop);
      consumer.start();
    }
    SyncingProducer producer = new SyncingProducer(buffer);
    producer.start();

  }
}
