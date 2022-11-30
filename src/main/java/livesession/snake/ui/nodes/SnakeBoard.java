package livesession.snake.ui.nodes;

import static java.lang.Math.floor;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import livesession.snake.Coordinate;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class BaseSnakeBoard.
 */
public class SnakeBoard extends GridPane {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SnakeBoard.class);

  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();
  private Map<Coordinate, SnakeBoardCell> cells = new HashMap<>();

  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }

  public void setSnakeModel(SnakeServiceViewModel snakeModel) {
    logger.debug("setSnakeModel");
    this.snakeModel.unbind();
    this.snakeModel.bind(snakeModel);
    createBoard();
  }

  public SnakeBoard() {
    logger.debug("constructor");
    setFocusTraversable(true);
  }

  private void createBoard() {
    cells.values().forEach(cell -> cell.unbind());
    cells.clear();
    getChildren().clear();
    int boardSize = snakeModel.getService().getConfiguration().getSize();
    logger.info("boardSize:{}", boardSize);
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
