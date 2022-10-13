package prog.ex05.solution.masterworker;

import java.util.List;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;

/**
 * Simple and straight-forward implementation of the Master interface.
 */
public class SimpleMaster implements Master {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleMaster.class);

  public SimpleMaster(int numberOfWorkers) {
  }

  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {
    return null;
  }

  @Override
  public TaskState getTaskState(final int taskId) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Task getTask(final int taskId) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getNumberOfWorkers() {
    return 0;
  }

  @Override
  public List<String> getWorkerNames() {
    return null;
  }

  @Override
  public int getNumberOfQueuedTasks() {
    return 0;
  }

  @Override
  public void shutdown() {
  }
}
