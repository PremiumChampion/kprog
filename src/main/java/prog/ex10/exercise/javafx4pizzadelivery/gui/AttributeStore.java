package prog.ex10.exercise.javafx4pizzadelivery.gui;

/**
 * Store for attributes.
 */
public interface AttributeStore {

  /**
   * Sets or overwrites an attribute.
   *
   * @param name   name / key of the attribute. Consists of at minimum one readable character
   * @param object object / value registered in the store using the key
   * @throws IllegalArgumentException if a parameter is a null reference or the name is invalid
   */
  public void setAttribute(String name, Object object) throws IllegalArgumentException;

  /**
   * returns the object registered under the given name.
   *
   * @param name name / key of the object
   * @return object / value stored under the given name
   * @throws IllegalArgumentException if no object is registered under the given name or the given
   *                                  name is invalid or a null reference
   */
  public Object getAttribute(String name) throws IllegalArgumentException;

  /**
   * removes the object registered under the given name.
   *
   * @param name name / key of the object
   * @throws IllegalArgumentException if no object is registered under the given name or the given
   *                                  name is invalid or a null reference
   */
  public void removeAttribute(String name) throws IllegalArgumentException;
}
