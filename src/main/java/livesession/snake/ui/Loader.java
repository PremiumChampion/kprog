package livesession.snake.ui;

/**
 * loader to load another ui component.
 *
 * @param <T> Data to inject into the Loadable component.
 */
public interface Loader<T> {

  /**
   * Load ui Component and show it.
   *
   * @param ui the component to load.
   */
  void load(Loadable<T> ui);
}
