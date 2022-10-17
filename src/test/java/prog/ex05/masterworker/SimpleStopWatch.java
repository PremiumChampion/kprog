package prog.ex05.masterworker;


import java.time.Duration;
import java.time.Instant;

public class SimpleStopWatch implements StopWatch {

  private Instant startTime;
  private Instant stopTime;
  private State state;

  public SimpleStopWatch() {
    state = State.RESET;
  }

  @Override
  public void startNow() {
    if (!state.equals(State.RESET)) {
      throw new IllegalStateException("StopWatch not in RESET state.");
    }
    startTime = Instant.now();
    state = State.STARTED;
  }

  @Override
  public Duration stopNow() {
    if (!state.equals(State.STARTED)) {
      throw new IllegalStateException("StopWatch not started.");
    }
    stopTime = Instant.now();
    state = State.STOPPED;
    return Duration.between(startTime, stopTime);
  }

  @Override
  public Instant getStartTime() throws IllegalStateException {
    if (state.equals(State.RESET)) {
      throw new IllegalStateException("Stop watch in RESET state. No start time available.");
    }
    return startTime;
  }

  @Override
  public Instant getStopTime() throws IllegalStateException {
    if (!state.equals(State.STOPPED)) {
      throw new IllegalStateException("Stop watch not in STOPPED mode. No stop time available.");
    }
    return stopTime;
  }

  @Override
  public void reset() {
    startTime = null;
    stopTime = null;
    state = State.RESET;
  }

  enum State {
    RESET, STARTED, STOPPED
  }

}
