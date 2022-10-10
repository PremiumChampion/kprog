package livesession.snake.provider;

import livesession.snake.BoardState;
import livesession.snake.Coordinate;

public class IllegalPositionException extends Exception{
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(IllegalPositionException.class);

  private Coordinate coordinate;
  private BoardState state;

  public IllegalPositionException(final Coordinate coordinate, final BoardState state) {
    this.coordinate = coordinate;
    this.state = state;
  }

  @Override
  public String getMessage() {
    return "Snake died at " + coordinate + " because of " + state;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public BoardState getState() {
    return state;
  }
}
