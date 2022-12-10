package prog.ex11.solution.saveandload.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
 * implementation uses Serialization and Deserialization with Object Streams.
 */
public class SerializingPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        new FileOutputStream(file))) {
      List<Object> orderMetadata = getOrderMetadata(order);
      List<Object> pizzaMetadata = order.getPizzaList().stream().flatMap(this::getPizzaMetadata)
          .collect(Collectors.toList());
      List<Object> combined = new ArrayList<>();
      combined.addAll(orderMetadata);
      combined.addAll(pizzaMetadata);
      for (Object obj : combined) {
        objectOutputStream.writeObject(obj);
      }
      objectOutputStream.flush();
    }
  }

  public List<Object> getOrderMetadata(Order order) {
    List<Object> orderMetadata = new ArrayList<>();

    orderMetadata.add(order.getOrderId());
    orderMetadata.add(order.getValue());
    orderMetadata.add(order.getPizzaList().size());
    return orderMetadata;
  }

  public Stream<Object> getPizzaMetadata(Pizza pizza) {
    List<Object> pizzaMetadata = new ArrayList<>();

    pizzaMetadata.add(pizza.getPizzaId());
    pizzaMetadata.add(pizza.getPrice());
    pizzaMetadata.add(pizza.getSize());
    pizzaMetadata.addAll(pizza.getToppings());

    return pizzaMetadata.stream();
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
      LinkedList<Object> containedObjects = new LinkedList<>();

      while (true) {
        try{
          containedObjects.add(objectInputStream.readObject());
        }catch(IOException e){
          break;
        }
      }

      Map<String, Object> orderMetadata = getOrderMetadata(containedObjects);
      List<Pizza> pizzaMetadata = getPizzaMetadata(containedObjects);

      if ((Integer) orderMetadata.get("pizzaCount") != pizzaMetadata.size()) {
        throw new WrongOrderFormatException(
            String.format("Invalid pizzacount: %d", (Integer) orderMetadata.get("pizzaCount")));
      }

      SimpleOrder order = new SimpleOrder((Integer) orderMetadata.get("orderId"));
      order.setPrice((Integer) orderMetadata.get("orderValue"));
      pizzaMetadata.forEach(order::addPizza);

      return order;
    } catch (ClassNotFoundException e) {
      throw new WrongOrderFormatException("Different version of serialised class", e);
    }
  }

  public Map<String, Object> getOrderMetadata(LinkedList<Object> data)
      throws WrongOrderFormatException {
    Map<String, Object> orderMetadata = new HashMap<>();

    try {

      int orderId = (Integer) data.remove(0);
      int orderValue = (Integer) data.remove(0);
      int pizzaCount = (Integer) data.remove(0);
      orderMetadata.put("orderId", orderId);
      orderMetadata.put("pizzaCount", pizzaCount);
      orderMetadata.put("orderValue", orderValue);

    } catch (ClassCastException e) {
      throw new WrongOrderFormatException("Got invalid value when parsing order.", e);
    }

    return orderMetadata;
  }

  public List<Pizza> getPizzaMetadata(LinkedList<Object> data) throws WrongOrderFormatException {
    List<Pizza> result = new ArrayList<>();

    while (data.size() > 0) {
      try {
        int pizzaId = (Integer) data.remove(0);
        int pizzaPrice = (Integer) data.remove(0);
        PizzaSize pizzaSize = (PizzaSize) data.remove(0);
        List<Topping> toppingList = new ArrayList<>();

        while (data.size() > 0 && Topping.class.isInstance(data.get(0))) {
          toppingList.add((Topping) data.remove(0));
        }
        SimplePizza pizza = new SimplePizza(pizzaId);
        pizza.setSize(pizzaSize);
        pizza.setPrice(pizzaPrice);
        toppingList.forEach(pizza::addTopping);

        result.add(pizza);

      } catch (ClassCastException e) {
        throw new WrongOrderFormatException("Got invalid value when parsing pizza.", e);
      }
    }

    return result;
  }

}
