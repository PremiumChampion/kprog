package livesession.snake.ui.nodes;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

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

  /**
   * creates a new snake cell.
   *
   * @param coordinate the coordinate of the cell.
   * @param size       the size of the cell for showing in the grid.
   */
  public SnakeBoardCell(Coordinate coordinate, double size) {
    super(size, size);
    this.coordinate = coordinate;
    setArcWidth(0);
    setStrokeWidth(0);
  }

  /**
   * updates the cells value.
   */
  public void updateValue(BoardState boardState, boolean isSnakeHead) {
    Paint color;
    switch (boardState) {
      case SNAKE:
        if (isSnakeHead) {
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
