package livesession.snake.ui;

/**
 * specifies that a component is able to be bound to. Replaces Initializable Interface for Base
 * components.
 */
public interface Bindable {

  /**
   * bind model to ui components.
   */
  void bind();
}
