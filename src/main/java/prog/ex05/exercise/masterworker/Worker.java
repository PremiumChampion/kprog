package prog.ex05.exercise.masterworker;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A Worker receives tasks to be executed using a queue. Since it is a non-blocking queue a
 * waiting time is defined to be used when the queue is empty.
 */
public interface Worker {
  long WAIT_EMPTY_QUEUE = 500;

  /**
   * Assigns a queue to the worker. This queue might be shared between master and all workers.
   *
   * @param queue queue containing task objects
   */
  void setQueue(ConcurrentLinkedQueue<Task> queue);

  /**
   * Returns the name of the worker. If the worker is a thread, this should be the name of the
   * thread.
   *
   * @return name of the worker
   */
  String getName();

  /**
   * Triggers the worker to terminate. If the worker is a thread, no brutal termination method
   * should be used. If the worker is executing a runnable, this execution should not be aborted.
   */
  void terminate();

}

