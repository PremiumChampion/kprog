package examples.helpers;

import java.time.Duration;
import java.time.Instant;

/**
 * This is only a simple prototype implementation of a StopWatch. It does not fulfill the
 * requirements of the StopWatch interface.
 */
public class PrototypeStopWatch implements StopWatch {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PrototypeStopWatch.class);

  private Instant startTime;
  private Instant endTime;

  @Override
  public void startNow() {
    startTime = Instant.now();
  }

  @Override
  public Duration stopNow() {
    endTime = Instant.now();
    return Duration.between(startTime, endTime);
  }
}
