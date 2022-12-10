package prog.ex11.solution.saveandload.factory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

/**
 * Simple and straight-forward implementation of the PersistenceFactory interface. This
 * implementation uses Data Stream to write and read primitive types in binary.
 */
public class BinaryPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      BinaryPersistenceFactory.class);
  static Map<Integer, Topping> toppingMap = new HashMap<>();
  static Map<Integer, PizzaSize> sizeMap = new HashMap<>();

  static {
    Arrays.stream(Topping.values()).forEach(topping -> toppingMap.put(topping.ordinal(), topping));
    Arrays.stream(PizzaSize.values()).forEach(size -> sizeMap.put(size.ordinal(), size));
  }

  @Override
  public void save(final File file, final Order order) throws IOException {
    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
      // orderId orderValue numberOfPizza
      // pizzaId pizzaValue pizzaSize toppingCount toppings...
      // additional pizzas
      dataOutputStream.writeInt(order.getOrderId());
      dataOutputStream.writeInt(order.getValue());
      dataOutputStream.writeInt(order.getPizzaList().size());
      for (Pizza pizza : order.getPizzaList()) {
        dataOutputStream.writeInt(pizza.getPizzaId());
        dataOutputStream.writeInt(pizza.getPrice());
        dataOutputStream.writeInt(pizza.getSize().ordinal());
        dataOutputStream.writeInt(pizza.getToppings().size());
        for (Topping topping : pizza.getToppings()) {
          dataOutputStream.writeInt(topping.ordinal());
        }
      }
      dataOutputStream.flush();
    }
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    // orderId orderValue numberOfPizza
    // pizzaId pizzaValue pizzaSize toppingCount toppings...
    // additional pizzas

    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
      return parseOrder(dataInputStream);
    }
  }

  public SimpleOrder parseOrder(DataInputStream dataInputStream) throws WrongOrderFormatException {
    // orderId orderValue numberOfPizza

    try {
      SimpleOrder order = new SimpleOrder(dataInputStream.readInt());

      order.setPrice(dataInputStream.readInt());

      int pizzaCount = dataInputStream.readInt();

      for (int i = 0; i < pizzaCount; i++) {
        order.addPizza(parsePizza(dataInputStream));
      }

      // Expect file to be at the end
      int available = dataInputStream.available();
      if (available != 0) {
        throw new WrongOrderFormatException(
            String.format("Expected EoF but got %d byte(s) left to read", available));
      }

      return order;
    } catch (IOException e) {
      throw new WrongOrderFormatException("An error occurred while parsing the order.", e);
    }
  }

  public SimplePizza parsePizza(DataInputStream dataInputStream) throws WrongOrderFormatException {
    try {
      // pizzaId pizzaValue pizzaSize toppingCount toppings...
      SimplePizza pizza = new SimplePizza(dataInputStream.readInt());
      pizza.setPrice(dataInputStream.readInt());
      int sizeOrdinal = dataInputStream.readInt();
      pizza.setSize(parseSize(sizeOrdinal));
      int toppingCount = dataInputStream.readInt();
      for (int i = 0; i < toppingCount; i++) {
        pizza.addTopping(parseTopping(dataInputStream.readInt()));
      }
      return pizza;
    } catch (IOException e) {
      throw new WrongOrderFormatException("An error occurred while parsing a pizza.", e);
    }
  }

  public PizzaSize parseSize(int ordinal) throws WrongOrderFormatException {
    if (!sizeMap.containsKey(ordinal)) {
      throw new WrongOrderFormatException(String.format("PizzaSize %d unknown.", ordinal));
    }
    return sizeMap.get(ordinal);
  }

  public Topping parseTopping(int ordinal) throws WrongOrderFormatException {
    if (!toppingMap.containsKey(ordinal)) {
      throw new WrongOrderFormatException(String.format("Topping %d unknown.", ordinal));
    }
    return toppingMap.get(ordinal);
  }
}
