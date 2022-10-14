package prog.ex05.exercise.masterworker;

/**
 * States of a Task.
 */
public enum TaskState {
  // state before a task gets assigned to a worker
  QUEUED,
  // when a worker receives the task, the state changes to RUNNING
  RUNNING,
  // when the runnable terminates without exception, the state is SUCCEEDED
  SUCCEEDED,
  // when the runnable aborts with an exception, the state is CRASHED
  CRASHED
}
