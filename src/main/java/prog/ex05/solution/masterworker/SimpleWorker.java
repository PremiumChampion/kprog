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
      if (this.queue == null) {
        continue;
      }
      // check if new task available
      Task currentTask = this.queue.poll();

      if (currentTask == null) {
        // wait for new tasks
        try {
          // should this be done?
          Thread.sleep(Worker.WAIT_EMPTY_QUEUE);
        } catch (InterruptedException e) {
          SimpleWorker.logger.error("Thread got interrupted", e);
        }
        continue;
      }

      currentTask.setState(TaskState.RUNNING);

      try {
        currentTask.getRunnable().run();
      } catch (Exception e) {
        currentTask.crashed(e);
        currentTask.setState(TaskState.CRASHED);
        continue;
      }

      currentTask.setState(TaskState.SUCCEEDED);
    }
  }

  @Override
  public void terminate() {
    this.markedForTermination = true;
  }
}
