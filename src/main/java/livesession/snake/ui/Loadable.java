package livesession.snake.ui;

import javafx.scene.Node;

public interface Loadable<T> {
  Node load();
  void bind(T thingToBind);
  void setLoader(Loader<T> loader);
}
