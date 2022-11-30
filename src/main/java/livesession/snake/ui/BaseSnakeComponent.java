package livesession.snake.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * class BaseSnakeComponent.
 */
public abstract class BaseSnakeComponent<Controller extends BaseSnakeUiController, Model extends BaseSnakeUiModel> implements
    Loadable<SnakeServiceViewModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BaseSnakeComponent.class);
  protected Loader<SnakeServiceViewModel> sceneLoader;
  protected FXMLLoader loader;
  protected Controller controller;
  protected Model model;

  public BaseSnakeComponent() {
    loader = new FXMLLoader(Objects.requireNonNull(getFXMLLocation(), "FXML-File not found."));

    try {
      model = getModelClass().getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
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
  protected abstract URL getFXMLLocation();

  protected abstract Class<Controller> getControllerClass();

  protected abstract Class<Model> getModelClass();
}
