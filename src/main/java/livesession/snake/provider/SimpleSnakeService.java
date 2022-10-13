package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;

public class SimpleSnakeService implements ExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);

  SimpleSnake snake;

  public SimpleSnakeService() {
    this.snake = new SimpleSnake(this);
  }

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
  public void configure(final GameConfiguration configuration) throws
      IllegalConfigurationException {

  }

  void triggeredByGameLoop() {
    try {
      advanceSnake();
    } catch (IllegalPositionException e) {
      failed(new Reason(e.getCoordinate(), e.getState()));
    }
  }

  @Override
  public Board getExternalBoard() {
    return null;
  }

  @Override
  public void failed(final Reason reason) {

  }

  public void advanceSnake() throws IllegalPositionException {
    snake.advance();
  }

  @Override
  public void addFood(final Coordinate coordinate) {

  }

  @Override
  public Snake getSnake() {
    return null;
  }

  public InternalBoard getInternalBoard() {
    return null;
  }

  @Override
  public void foodEaten(Coordinate coordinate) {

  }

  @Override
  public void updateScore(final BoardState state) {

  }
}
