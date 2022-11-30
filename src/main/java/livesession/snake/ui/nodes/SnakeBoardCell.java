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

  public static final Paint GRASS = Color.web("#6A994E");
  public static final Paint SNAKE_BODY = Color.web("#FFD500");
  public static final Paint SNAKE_HEAD = Color.web("#F1A208");
  public static final Paint FOOD = Color.web("#BC4749");
  public static final Paint WALL = Color.web("#9C6644");

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SnakeBoardCell.class);
  private final Coordinate coordinate;
  private SnakeServiceViewModel model;
  private InvalidationListener onChange = this::boardChanged;

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

  public void unbind() {
    this.model.boardProperty().removeListener(onChange);
  }

  private void boardChanged(Observable observable) {
    updateCellValue();
  }

  private void updateCellValue() {
    Coordinate head = model.getService().getSnake().getPosition().get(0);
    Board board = model.getBoard();
    BoardState boardState = board.getStateFromPosition(coordinate);
    Paint color = GRASS;
    switch (boardState) {
      case SNAKE:
        if (coordinate.equals(head)) {
          color = SNAKE_HEAD;
        } else {
          color = SNAKE_BODY;
        }
        break;
      case GRASS:
        color = GRASS;
        break;
      case WALL:
        color = WALL;
        break;
      case FOOD:
        color = FOOD;
        break;
    }
    setFill(color);
  }
}
