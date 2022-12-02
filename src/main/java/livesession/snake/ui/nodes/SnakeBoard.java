package livesession.snake.ui.nodes;

import static java.lang.Math.floor;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import livesession.snake.Coordinate;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class SnakeBoard.
 */
public class SnakeBoard extends GridPane {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SnakeBoard.class);

  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();
  private Map<Coordinate, SnakeBoardCell> cells = new HashMap<>();

  /**
   * create a new snake model.
   */
  public SnakeBoard() {
    logger.debug("constructor");
    setFocusTraversable(true);
  }

  /**
   * sets a new snake model.
   *
   * @param snakeModel the new snake model.
   */
  public void setSnakeModel(SnakeServiceViewModel snakeModel) {
    logger.debug("setSnakeModel");
    this.snakeModel.unbind();
    this.snakeModel.bind(snakeModel);
    createBoard();
  }

  /**
   * creates a new Board and bind events.
   */
  private void createBoard() {
    cells.values().forEach(SnakeBoardCell::unbind);
    cells.clear();
    getChildren().clear();
    int boardSize = snakeModel.getService().getConfiguration().getSize();
    logger.info("configuration: size:{}", boardSize);
    double cellSize = floor(getPrefWidth() / boardSize);
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        Coordinate coordinate = new Coordinate(row, col);
        SnakeBoardCell boardCell = new SnakeBoardCell(snakeModel, coordinate, cellSize);
        cells.put(coordinate, boardCell);
        add(boardCell, coordinate.getColumn(), coordinate.getRow());
      }
    }
  }
}
