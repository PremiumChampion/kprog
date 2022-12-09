package livesession.snake.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Implements base actions for a snake screen, synchronises controller, property binding and
 * dependency injection.
 *
 * @param <C> the controller of the screen.
 */
public abstract class BaseSnakeScreen<C extends BaseSnakeController> {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      BaseSnakeScreen.class);
  protected SnakeScreenLoader sceneLoader;
  protected FXMLLoader fxmlLoader;
  protected C controller;
  protected SnakeServiceViewModel model;

  /**
   * constructs a new snake screen.
   */
  public BaseSnakeScreen() {
    fxmlLoader = new FXMLLoader(Objects.requireNonNull(getFxmlLocation(), "FXML-File not found."));
    model = new SnakeServiceViewModel();

    try {
      this.fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.controller = fxmlLoader.getController();
  }

  public Node load() {
    return this.fxmlLoader.getRoot();
  }

  public void setScreenLoader(SnakeScreenLoader loader) {
    this.sceneLoader = loader;
    controller.setScreenLoader(this.sceneLoader);
  }


  public void bind(SnakeServiceViewModel snakeModel) {
    model.bind(snakeModel);
    controller.bind(snakeModel);
  }

  /**
   * Location of the fxml file to load. file needs controller to specified.
   *
   * @return the location of the file.
   */
  protected abstract URL getFxmlLocation();
}
