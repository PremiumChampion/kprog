package livesession.snake.provider;

import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;

public class SimpleSnakeService implements SnakeService {
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
  public void addListener(final SnakeListener listener) {

  }

  @Override
  public void removeListener(final SnakeListener listener) {

  }

  @Override
  public void configure(final GameConfiguration configuration) throws
      IllegalConfigurationException {

  }

  void triggeredByGameLoop() {
    try {
      advanceSnake();
    } catch (IllegalPositionException e) {
      failed(e.getMessage());
    }
  }

  void advanceSnake() throws IllegalPositionException {
    snake.advance();
  }
  @Override
  public Snake getSnake() {
    return null;
  }

  void failed(String reason) {

  }

  InternalBoard getInternalBoard() {
    return null;
  }
}
