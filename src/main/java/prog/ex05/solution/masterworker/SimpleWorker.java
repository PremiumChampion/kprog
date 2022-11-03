package prog.ex05.solution.masterworker;

import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.exercise.masterworker.Worker;

/**
 * Simple and straight-forward implementation of the Worker interface.
 */
public class SimpleWorker extends Thread implements Worker {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SimpleWorker.class);

  public boolean markedForTermination = false;
  public ConcurrentLinkedQueue<Task> queue;

  public SimpleWorker(String name) {
    super(name);
  }

  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    if (this.queue == null) {
      logger.error("Queue is not set.");
      return;
    }

    while (!markedForTermination) {
      // check if new task available
      Task currentTask = this.queue.poll();

      if (currentTask == null) {
        // wait for new tasks
        try {
          synchronized (this.queue) {
            this.queue.wait();
            logger.info("got wakeup signal");
          }
        } catch (InterruptedException e) {
          logger.error("Thread got interrupted for some reason", e);
          markedForTermination = true;
        }
        continue;
      }

      currentTask.setState(TaskState.RUNNING);

      try {
        currentTask.getRunnable().run();
        currentTask.setState(TaskState.SUCCEEDED);
      } catch (Exception e) {
        currentTask.crashed(e);
        currentTask.setState(TaskState.CRASHED);
      }
    }
  }

  @Override
  public void terminate() {
    logger.info("IllBeBack");
    this.markedForTermination = true;
  }
}
