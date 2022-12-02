package livesession.snake.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Implements base actions for a snake screen, synchronises controller, property binding and
 * dependency injection.
 *
 * @param <Controller> the controller of the screen.
 * @param <Model>      the model of the screen.
 */
public abstract class BaseSnakeScreen<
    Controller extends BaseSnakeUiController,
    Model extends BaseSnakeUiModel>
    implements Loadable<SnakeServiceViewModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BaseSnakeScreen.class);
  protected Loader<SnakeServiceViewModel> sceneLoader;
  protected FXMLLoader loader;
  protected Controller controller;
  protected Model model;

  /**
   * constructs a new snake screen.
   */
  public BaseSnakeScreen() {
    loader = new FXMLLoader(Objects.requireNonNull(getFxmlLocation(), "FXML-File not found."));

    try {
      model = getModelClass().getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException
             | NoSuchMethodException e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    try {
      this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Object controller = loader.getController();

    if (!getControllerClass().isInstance(controller)) {
      throw new IllegalArgumentException(String.format("Controller is of type %s but expected %s",
          controller.getClass().getSimpleName(), getControllerClass().getSimpleName()));
    }
    this.controller = (Controller) controller;

    this.controller.setModel(model);

  }

  @Override
  public Node load() {
    return this.loader.getRoot();
  }

  @Override
  public void setLoader(Loader<SnakeServiceViewModel> loader) {
    this.sceneLoader = loader;
  }

  @Override
  public void bind(SnakeServiceViewModel snakeModel) {
    model.getSnakeModel().bind(snakeModel);
    this.controller.bind();
  }

  /**
   * Location of the fxml file to load. file needs controller to specified.
   *
   * @return the location of the file.
   */
  protected abstract URL getFxmlLocation();

  /**
   * class for the controller used for type checking.
   *
   * @return the controller class.
   */
  protected abstract Class<Controller> getControllerClass();

  /**
   * class for the model. used to generate a shared model.
   *
   * @return the model class.
   */
  protected abstract Class<Model> getModelClass();
}
