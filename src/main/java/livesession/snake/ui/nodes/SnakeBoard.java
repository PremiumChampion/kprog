package livesession.snake.ui.nodes;

import static java.lang.Math.floor;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;
import livesession.snake.Board;
import livesession.snake.Coordinate;

/**
 * class SnakeBoard.
 */
public class SnakeBoard extends GridPane {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SnakeBoard.class);
  ObjectProperty<Coordinate> snakeHead = new SimpleObjectProperty<>();
  private final ObjectProperty<Board> board = new SimpleObjectProperty<>();
  private final IntegerProperty boardSize = new SimpleIntegerProperty(0);
  private final Map<Coordinate, SnakeBoardCell> cells = new HashMap<>();

  /**
   * create a new snake model.
   */
  public SnakeBoard() {
    logger.debug("constructor");
    setFocusTraversable(true);
    addListener();
  }

  private void addListener() {
    this.board.addListener(property -> updateBoard());
    this.snakeHead.addListener(property -> updateBoard());
  }

  /**
   * binds a board.
   *
   * @param board to bind to.
   */
  public void bindBoard(ObjectProperty<Board> board) {
    logger.debug("bind board model");
    this.board.bind(board);
    checkForUpdatedBoardSize();
    updateBoard();
  }

  private void checkForUpdatedBoardSize() {
    if (this.board.get().size() != boardSize.get()) {
      boardSize.set(this.board.get().size());
      createBoard();
    }
  }

  /**
   * binds the new head position.
   *
   * @param newHead the new head position.
   */
  public void bindHead(ObjectProperty<Coordinate> newHead) {
    logger.debug("bind head model");
    snakeHead.bind(newHead);
    updateBoard();
  }

  /**
   * creates a new Board and bind events.
   */
  private void createBoard() {
    logger.debug("create board {} {}", board.get(), snakeHead.get());

    if (board.get() == null) {
      return;
    }

    cells.clear();
    getChildren().clear();
    int boardSize = board.get().size();
    logger.info("configuration: size:{}", boardSize);
    double cellSize = floor(getPrefWidth() / boardSize);
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        Coordinate coordinate = new Coordinate(row, col);
        SnakeBoardCell boardCell = new SnakeBoardCell(coordinate, cellSize);
        cells.put(coordinate, boardCell);
        add(boardCell, coordinate.getColumn(), coordinate.getRow());
      }
    }
    logger.debug("create end {}", cells.size());
  }

  /**
   * applies new values to the cells -> cells are dumb.
   */
  private void updateBoard() {
    checkForUpdatedBoardSize();

    int bordWidth = boardSize.get();
    logger.debug("update board {} {}", cells.size(), bordWidth);
    if (cells.size() != bordWidth * bordWidth || snakeHead.get() == null) {
      return;
    }
    logger.info("updating board");
    for (int row = 0; row < bordWidth; row++) {
      for (int col = 0; col < bordWidth; col++) {
        Coordinate coordinate = new Coordinate(row, col);
        SnakeBoardCell boardCell = cells.get(coordinate);
        boardCell.updateValue(board.get().getStateFromPosition(coordinate),
            snakeHead.get().equals(coordinate));
      }
    }
  }
}
