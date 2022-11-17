package prog.ex10.solution.javafx4pizzadelivery.gui;

/**
 * comment.
 *
 * @param <T> genric param.
 */
public interface OnRemoveEvent<T> {

  /**
   * called on remove.
   *
   * @param item item to remove.
   */
  void remove(T item);
}
