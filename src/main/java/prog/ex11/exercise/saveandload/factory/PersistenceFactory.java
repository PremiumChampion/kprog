package prog.ex11.exercise.saveandload.factory;


import java.io.File;
import java.io.IOException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;

/**
 * Interface for persistence actions in the PizzaDelivery environment.
 */
public interface PersistenceFactory {
  /**
   * Saves an Order object into the given File.
   *
   * @param file file into which the Order object shall be saved
   * @param order Order to be saved
   * @throws IOException if the file cannot be written
   */
  void save(File file, Order order) throws IOException;

  /**
   * Loads an Order object from the given File.
   *
   * @param file file where the Order object is stored
   * @return Order object which is stored in the given File
   * @throws IOException if the file does not exist or cannot be opened or cannot be read
   * @throws WrongOrderFormatException if the content of the file cannot successfully be parsed
   *         into an Order object
   */
  Order load(File file) throws IOException, WrongOrderFormatException;
}
