package livesession.snake.provider;

import static org.junit.Assert.assertEquals;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import org.junit.Before;
import org.junit.Test;

/**
 * class TestSimpleSnakeService.
 */
public class TestSimpleSnakeService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSimpleSnakeService.class);
  private SimpleSnakeService service;

  @Before
  public void setup() {
    this.service = new SimpleSnakeService();
  }
  @Test
  public void TestDefaultRandomlyPlacedFood() {
    int expectedFoodCount = 1;
    int presentFoodCount = 0;
    Board externalBoard = this.service.getExternalBoard();

    for (int i = 0; i < externalBoard.size(); i++) {
      for (int j = 0; j < externalBoard.size(); j++) {
        if (externalBoard.getStateFromPosition(i, j) == BoardState.FOOD) {
          presentFoodCount++;
        }
      }
    }

    assertEquals(expectedFoodCount,presentFoodCount);
  }
  @Test
  public void TestNewConfigurationRandomlyPlacedFood() throws IllegalConfigurationException {
    int expectedFoodCount = 4;
    int presentFoodCount = 0;
    this.service.configure(new GameConfiguration(20,500,expectedFoodCount));
    Board externalBoard = this.service.getExternalBoard();

    for (int i = 0; i < externalBoard.size(); i++) {
      for (int j = 0; j < externalBoard.size(); j++) {
        if (externalBoard.getStateFromPosition(i, j) == BoardState.FOOD) {
          presentFoodCount++;
        }
      }
    }

    assertEquals(expectedFoodCount,presentFoodCount);
  }
}
