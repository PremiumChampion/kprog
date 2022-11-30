package livesession.snake.ui;

public interface Loader<T> {
  void load(Loadable<T> ui);
}
