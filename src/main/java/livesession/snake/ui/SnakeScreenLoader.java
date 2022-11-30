package livesession.snake.ui;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.scene.Group;
import javafx.scene.Scene;
import livesession.snake.GameState;
import livesession.snake.Reason;
import livesession.snake.Reason.Mode;
import livesession.snake.ui.gameover.GameOver;
import livesession.snake.ui.mainmenu.MainMenu;
import livesession.snake.ui.pause.Pause;
import livesession.snake.ui.play.Play;

/**
 * class SnakeScreenLoader.
 */
public class SnakeScreenLoader implements Loader<SnakeServiceViewModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SnakeScreenLoader.class);
  private Group root = new Group();
  private Scene scene = new Scene(root);
  private final SnakeServiceViewModel MASTER_MODEL = new SnakeServiceViewModel(true);

  public SnakeScreenLoader() {
    MASTER_MODEL.gameStateProperty().addListener(this::gameStateChanged);
    MASTER_MODEL.reasonProperty().addListener(this::reasonChanged);
  }

  private void reasonChanged(Observable observable) {
    setAbortScreen();
  }

  private void setAbortScreen() {
    Reason reason = MASTER_MODEL.getReason();
    if (reason == null) {
      // do nothing
    }
    if (reason != null) {
      Mode mode = reason.getMode();

      if (mode == Mode.COORDINATE) {
        load(new GameOver());
      }
      if (mode == Mode.MESSAGE) {
        load(new GameOver());
      }
    }
  }

  private void gameStateChanged(Observable observable) {
    GameState gameState = MASTER_MODEL.getGameState();
    logger.info("game-state changed: {}", gameState);
    switch (gameState) {
      case ABORTED:
        // do aborted stuff
        setAbortScreen();
        break;
      case PAUSED:
        this.load(new Pause());
        // do paused stuff
        break;
      case RUNNING:
        this.load(new Play());
        // do running stuff
        break;
      case PREPARED:
        // do prepared stuff
        this.load(new MainMenu());
        break;
      default:
        logger.warn(
            String.format("invalid game state: %s", gameState));
    }
  }

  public Scene getScene() {
    return scene;
  }

  public void load(Loadable<SnakeServiceViewModel> ui) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> load(ui));
      return;
    }

    root.getChildren().clear();
    ui.setLoader(this);
    ui.bind(MASTER_MODEL);

    root.getChildren().add(ui.load());
  }

}
