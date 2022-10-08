package livesession.snake.provider;

import static org.junit.Assert.assertEquals;

import livesession.snake.Board;
import org.junit.Before;
import org.junit.Test;

public class TestBaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestBaseBoard.class);
  private static final int DEFAULT_BOARD_SIZE = 11;

  private Board defaultBoard;

  @Before
  public void setup() {
    defaultBoard = new BaseBoard(DEFAULT_BOARD_SIZE);
  }

  @Test
  public void testGetSize() {
    assertEquals("Size should be " + DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE, defaultBoard.size());
  }
}
