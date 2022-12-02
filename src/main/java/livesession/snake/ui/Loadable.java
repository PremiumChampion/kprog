package livesession.snake.ui;

import javafx.scene.Node;

/**
 * Ui component can be loaded with a Loader.
 *
 * @param <T> controller type.
 */
public interface Loadable<T> {

  /**
   * get node to set as children.
   *
   * @return the node to load.
   */
  Node load();

  /**
   * bind dependencies into the ui.
   *
   * @param thingToBind value to bind.
   */
  void bind(T thingToBind);

  /**
   * inject loader as a dependency.
   *
   * @param loader the loader.
   */
  void setLoader(Loader<T> loader);
}
