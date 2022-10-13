package prog.ex05.exercise.masterworker;

import java.util.List;

/**
 * A Master is the coordinator in the master-worker design pattern.
 * The master receives runnables to be worked on by the worker threads. Depending on the
 * executing machine, the number of worker threads can be parameterized in the constructor
 * of an implementation of the Master interface.
 */
public interface Master {
  /**
   * Adds a task to the master-worker team.
   *
   * @param runnable Runnable to be executed by one of the workers
   * @return A Task object with a unique id and the state of the task
   * @throws IllegalArgumentException if the runnable is a null reference
   */
  Task addTask(Runnable runnable) throws IllegalArgumentException;

  /**
   * Returns the actual TaskState of the task.
   *
   * @param taskId id of the task
   * @return actual TaskState
   * @throws IllegalArgumentException if the taskId is not known within the Master
   */
  TaskState getTaskState(int taskId) throws IllegalArgumentException;

  /**
   * Returns the task object belonging to the taskId.
   *
   * @param taskId id of the requested task
   * @return Task object belonging to the taskId
   * @throws IllegalArgumentException if the taskId is not known within the Master
   */
  Task getTask(int taskId) throws IllegalArgumentException;

  /**
   * Returns the number of worker threads configured.
   *
   * @return number of worker threads
   */
  int getNumberOfWorkers();

  /**
   * Returns a list of the names of the worker threads.
   *
   * @return List of String containing the names of the worker threads
   */
  List<String> getWorkerNames();


  /**
   * Returns the number of queued tasks.
   *
   * @return number of tasks which are not yet taken by the workers.
   */
  int getNumberOfQueuedTasks();

  /**
   * Shuts the whole master-worker team down.
   * It triggers the termination of the worker threads.
   */
  void shutdown();

}
