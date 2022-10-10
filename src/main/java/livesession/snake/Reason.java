package livesession.snake;

public class Reason {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Reason.class);

  private Coordinate coordinate;
  private BoardState state;
  private String message;
  private Mode mode;

  public Reason(final Coordinate coordinate, final BoardState state) {
    this.coordinate = coordinate;
    this.state = state;
    mode = Mode.COORDINATE;
  }

  public Reason(final String message) {
    this.message = message;
    mode = Mode.MESSAGE;
  }

  public Coordinate getCoordinate() {
    if (mode.equals(Mode.MESSAGE)) {
      throw new IllegalStateException("Reason is in message mode.");
    }
    return coordinate;
  }

  public BoardState getState() {
    if (mode.equals(Mode.MESSAGE)) {
      throw new IllegalStateException("Reason is in message mode.");
    }
    return state;
  }

  public String getMessage() {
    if (mode.equals(Mode.COORDINATE)) {
      throw new IllegalStateException("Reason is in coordinate mode.");
    }
    return message;
  }

  public Mode getMode() {
    return mode;
  }

  enum Mode {
    MESSAGE, COORDINATE
  }
}
