package livesession.snake.provider;

import livesession.snake.Coordinate;

public class FakeSnakeService extends DummyExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FakeSnakeService.class);

  FakeInternalBoard fakeBoard;
  boolean eaten = false;

  public FakeSnakeService(final FakeInternalBoard fakeBoard) {
    this.fakeBoard = fakeBoard;
  }


  @Override
  public void foodEaten(final Coordinate coordinate) {
    eaten = true;

  }

  @Override
  public InternalBoard getInternalBoard() {
    return fakeBoard;
  }

  public boolean isEaten() {
    return eaten;
  }
}
