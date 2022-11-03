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
    while (!markedForTermination) {
      // check if new task available
      Task currentTask = this.queue.poll();

      if (currentTask == null) {
        try {
          synchronized (this.queue){
            logger.info("waiting for task");
            this.queue.wait();
            logger.info("wakeup");
          }
        } catch (InterruptedException e) {
          SimpleWorker.logger.error("Thread got interrupted", e);
          this.markedForTermination = true;
          return;
        }
        continue;
      }

      logger.info("running task with id",currentTask.getId());
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
    this.markedForTermination = true;
  }
}
