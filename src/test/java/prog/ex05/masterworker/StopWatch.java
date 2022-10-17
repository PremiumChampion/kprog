package prog.ex05.masterworker;

import java.time.Duration;
import java.time.Instant;

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

  /**
   * Returns time stamp of start time.
   *
   * @return start time as a java Instant object
   * @throws IllegalStateException if the stop watch hasn't been started.
   */
  Instant getStartTime() throws IllegalStateException;

  /**
   * Returns time stamp of stop time.
   *
   * @return stop time as a java Instant object
   * @throws IllegalStateException if the stop watch hasn't been stopped nor started.
   */
  Instant getStopTime() throws IllegalStateException;

  /**
   * Resets the stop watch to be used in another stop watch action.
   */
  void reset();

}
