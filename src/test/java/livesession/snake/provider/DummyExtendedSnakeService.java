package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.Reason;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;

public class DummyExtendedSnakeService implements ExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DummyExtendedSnakeService.class);

  @Override
  public void reset() {

  }

  @Override
  public void start() {

  }

  @Override
  public void abort() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void moveLeft() {

  }

  @Override
  public void moveRight() {

  }

  @Override
  public boolean addListener(final SnakeListener listener) {
    return false;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    return false;
  }

  @Override
  public void configure(final GameConfiguration configuration) {

  }

  @Override
  public GameConfiguration getConfiguration() {
    return null;
  }

  @Override
  public Snake getSnake() {
    return null;
  }

  @Override
  public Board getBoard() {
    return null;
  }

  @Override
  public Board getExternalBoard() {
    return null;
  }

  @Override
  public void failed(final Reason reason) {

  }

  @Override
  public void triggeredByGameLoop() {

  }

  @Override
  public void advanceSnake() throws IllegalPositionException {

  }

  @Override
  public void addFood(final Coordinate coordinate) {

  }

  @Override
  public void foodEaten(final Coordinate coordinate) {

  }

  @Override
  public void updateScore(final BoardState state) {

  }

  @Override
  public InternalBoard getInternalBoard() {
    return null;
  }
}
