package prog.ex05.exercise.masterworker;

/**
 * A Task object represents a task managing the execution of a runnable in the master-worker
 * design pattern.
 */
public class Task {
  // Static counter to create unique ids within this java process
  private static int idCounter = 0;
  // The runnable to be executed.
  Runnable runnable;
  // id of the task. The id must be unique within a java process.
  private int id;
  // State of the task. The first state must be QUEUED.
  private TaskState state;
  // Exception to be returned if the runnable crashes during execution
  private Exception exception;

  /**
   * Constructor to create a task on the Basis of a runnable.
   *
   * @param runnable runnable to be executed
   * @throws IllegalArgumentException if the runnable is a null reference
   */
  public Task(final Runnable runnable) throws IllegalArgumentException {
    if (runnable == null) {
      throw new IllegalArgumentException("runnable is null");
    }
    id = ++idCounter;
    this.runnable = runnable;
    state = TaskState.QUEUED;
  }

  public int getId() {
    return id;
  }

  public TaskState getState() {
    return state;
  }

  public void setState(final TaskState state) {
    this.state = state;
  }

  public Runnable getRunnable() {
    return runnable;
  }

  public void crashed(final Exception e) {
    this.exception = e;
  }

  public Exception getException() {
    return exception;
  }
}
