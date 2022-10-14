package examples.helpers;

import java.time.Duration;

/**
 * Stop watch to measure execution time of multithreading tasks.
 */
public interface StopWatch {

  /**
   * Starts the stop watch.
   *
   * @throws IllegalStateException if the stop watch is already started.
   */
  void startNow() throws IllegalStateException;

  /**
   * Stops the stop watch.
   *
   * @return time difference between start and stop
   * @throws IllegalStateException if the stop watch hasn't started.
   */
  Duration stopNow() throws IllegalStateException;
}
