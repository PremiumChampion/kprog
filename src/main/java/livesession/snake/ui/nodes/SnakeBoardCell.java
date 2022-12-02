package livesession.snake.ui.nodes;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class SnakeBoardCell.
 */
public class SnakeBoardCell extends Rectangle {

  public static final Paint GRASS_LIGHT = Color.web("#6A994E");
  public static final Paint GRASS_DARK = Color.web("#67944d");
  public static final Paint SNAKE_BODY = Color.web("#FFD500");
  public static final Paint SNAKE_HEAD = Color.web("#F1A208");
  public static final Paint FOOD = Color.web("#BC4749");
  public static final Paint WALL = Color.web("#9C6644");

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SnakeBoardCell.class);
  private final Coordinate coordinate;
  private SnakeServiceViewModel model;
  private InvalidationListener onChange = this::boardChanged;

  /**
   * creates a new snake cell.
   *
   * @param model      the model to show data from.
   * @param coordinate the coordinate of the cell.
   * @param size       the size of the cell for showing in the grid.
   */
  public SnakeBoardCell(SnakeServiceViewModel model, Coordinate coordinate, double size) {
    super(size, size);
    logger.debug("constructor {} {}", coordinate, size);
    this.model = model;
    this.coordinate = coordinate;
    this.model.boardProperty().addListener(onChange);
    updateCellValue();
    setArcWidth(0);
    setStrokeWidth(0);
  }

  /**
   * called before snake is removed.
   */
  public void unbind() {
    this.model.boardProperty().removeListener(onChange);
  }

  /**
   * handler when the board changed.
   *
   * @param observable board property.
   */
  private void boardChanged(Observable observable) {
    updateCellValue();
  }

  /**
   * updates the cells value.
   */
  private void updateCellValue() {
    Coordinate head = model.getService().getSnake().getPosition().get(0);
    Board board = model.getBoard();
    BoardState boardState = board.getStateFromPosition(coordinate);
    Paint color = GRASS_LIGHT;
    switch (boardState) {
      case SNAKE:
        if (coordinate.equals(head)) {
          color = SNAKE_HEAD;
        } else {
          color = SNAKE_BODY;
        }
        break;
      case GRASS:
        boolean rowIsEven = coordinate.getRow() % 2 == 0;
        boolean colIsEven = coordinate.getColumn() % 2 == 0;

        color = rowIsEven ? (colIsEven ? GRASS_LIGHT : GRASS_DARK)
            : colIsEven ? GRASS_DARK : GRASS_LIGHT;
        break;
      case WALL:
        color = WALL;
        break;
      case FOOD:
        color = FOOD;
        break;
      default:
        throw new IllegalArgumentException("Did not expect:" + boardState);
    }
    setFill(color);
  }
}
