package prog.ex11.solution.saveandload.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;

/**
 * Simple and straight-forward implementation of the PersistenceFactory interface. This
 * implementation uses Serialization and Deserialization with Object Streams.
 */
public class SerializingPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        new FileOutputStream(file))) {
      objectOutputStream.writeObject(order);
      objectOutputStream.flush();
    }
  }


  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
      return (Order) objectInputStream.readObject();
    } catch (ClassNotFoundException e) {
      throw new WrongOrderFormatException("Different version of serialised class", e);
    }
  }
}
