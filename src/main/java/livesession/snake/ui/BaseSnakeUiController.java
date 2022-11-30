package livesession.snake.ui;


public interface BaseSnakeUiController<T> extends Bindable {

  void setModel(T model);
}
