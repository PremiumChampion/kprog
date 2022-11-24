package examples.threads.simplequeue;

import java.util.Queue;

/**
 *  Simple Thread that munches Food from a queue. Complains when the expected Food is a null
 *  reference.
 */
public class MunchingThread extends Thread {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MunchingThread.class);

  private Queue<Food> queue;
  private static int idCounter = 1;
  private boolean running = true;

  /**
   * Constructor.
   *
   * @param queue queue the thread gets its Food from
   */
  public MunchingThread(final Queue<Food> queue) {
    super("munchingThread- " + idCounter++);
    this.queue = queue;
    this.setDaemon(true);
    this.start();
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(final boolean running) {
    this.running = running;
  }

  @Override
  public void run() {
    logger.debug("Thread " + getName() + " is started.");

    while (running) {
      int queueSize = queue.size();
      if (queueSize > 0) {
        logger.debug("Queue size is " + queueSize);
        Food elem = queue.poll();
        munchFood(elem);
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private void munchFood(final Food elem) {
    if (elem == null) {
      logger.info("UUrrrgh. This is a null food!");
    } else {
      logger.info("Hmmm. munching " + elem.getName());
    }
  }
}
