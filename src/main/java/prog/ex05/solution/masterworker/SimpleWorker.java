package prog.ex05.solution.masterworker;


import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.Worker;

/**
 * Simple and straight-forward implementation of the Worker interface.
 */
public class SimpleWorker implements Worker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleWorker.class);

  public SimpleWorker(String name) {
  }

  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {

  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void terminate() {

  }
}
