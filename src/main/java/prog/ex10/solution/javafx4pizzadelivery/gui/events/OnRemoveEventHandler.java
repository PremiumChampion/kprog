package prog.ex10.solution.javafx4pizzadelivery.gui.events;

/**
 * comment.
 *
 * @param <T> generic param.
 */
public interface OnRemoveEventHandler<T> {

  /**
   * called on remove.
   *
   * @param item item to remove.
   */
  void remove(T item);
}
