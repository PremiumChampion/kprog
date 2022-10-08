package livesession.snake;

public class Reason {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Reason.class);

  private Coordinate coordinate;
  private BoardState state;

  public Reason(final Coordinate coordinate, final BoardState state) {
    this.coordinate = coordinate;
    this.state = state;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public BoardState getState() {
    return state;
  }
}
