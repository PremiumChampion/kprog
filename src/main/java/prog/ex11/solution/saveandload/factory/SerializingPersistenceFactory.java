package prog.ex11.solution.saveandload.factory;

import java.io.File;
import java.io.IOException;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;

/**
 * Simple and straight-forward implementation of the PersistenceFactory interface.
 * This implementation uses Serialization and Deserialization with Object Streams.
 */
public class SerializingPersistenceFactory implements PersistenceFactory {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {

  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    return null;
  }
}
