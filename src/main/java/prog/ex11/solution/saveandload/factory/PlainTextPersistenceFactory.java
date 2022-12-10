package prog.ex11.solution.saveandload.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
 * implementation uses text in a csv format.
 */
public class PlainTextPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PlainTextPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {
    try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
      String orderString = createOrderString(order);
      List<String> pizzaStrings = order.getPizzaList().stream().map(this::createPizzaString)
          .collect(
              Collectors.toList());
      String pizzaString = String.join("\n", pizzaStrings);

      printWriter.println(orderString);
      printWriter.println(pizzaString);

      printWriter.flush();
    }
  }

  private String createPizzaString(Pizza pizza) {
    String basePizzaString = String.format("%d;%d;%s", pizza.getPizzaId(), pizza.getPrice(),
        pizza.getSize());
    String toppingPizzaString = String.join(";",
        pizza.getToppings().stream().map(Objects::toString).collect(Collectors.toList()));
    String wholeOrder = basePizzaString;
    if (toppingPizzaString.length() != 0) {
      wholeOrder = wholeOrder + ";" + toppingPizzaString;
    }
    return wholeOrder;
  }

  private String createOrderString(Order order) {
    return String.format("%d;%d;%d", order.getOrderId(), order.getValue(),
        order.getPizzaList().size());
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

      String orderMetadata = bufferedReader.readLine();
      orderMetadata = orderMetadata.trim();
      String[] orderTokens = orderMetadata.split(";");

      SimpleOrder order = parseOrder(orderTokens);

      for (String line : bufferedReader.lines().toArray(String[]::new)) {
        order.addPizza(parsePizza(line));
      }

      if (parseOrderPizzaCount(orderTokens) != order.getPizzaList().size()) {
        throw new WrongOrderFormatException("Pizza count does not match expectations.");
      }

      return order;
    }
  }


  public SimpleOrder parseOrder(String[] orderTokens) throws WrongOrderFormatException {
    assertOrderTokenLength(orderTokens);
    SimpleOrder order = new SimpleOrder(parseOrderId(orderTokens));

    order.setPrice(parseOrderPrice(orderTokens));

    return order;
  }

  private void assertOrderTokenLength(String[] orderTokens) throws WrongOrderFormatException {
    if (orderTokens.length != 3) {
      throw new WrongOrderFormatException("Expected 3 entries but got " + orderTokens.length);
    }
  }

  public int parseOrderId(String[] tokinisedOrder) throws WrongOrderFormatException {
    assertOrderTokenLength(tokinisedOrder);
    try {
      return Integer.parseInt(tokinisedOrder[0]);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException(String.format("Invalid orderId: %s", tokinisedOrder[0]),
          e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no orderId set", e);
    }
  }

  private int parseOrderPrice(String[] tokinisedOrder) throws WrongOrderFormatException {
    assertOrderTokenLength(tokinisedOrder);
    try {
      return Integer.parseInt(tokinisedOrder[1]);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException(String.format("Invalid price: %s", tokinisedOrder[1]),
          e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no price set", e);
    }
  }

  private int parseOrderPizzaCount(String[] tokinisedOrder) throws WrongOrderFormatException {
    assertOrderTokenLength(tokinisedOrder);
    try {
      return Integer.parseInt(tokinisedOrder[2]);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException(
          String.format("Invalid pizzacount: %s", tokinisedOrder[2]),
          e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no pizzacount set", e);
    }
  }

  public Pizza parsePizza(String serialisedPizza) throws WrongOrderFormatException {

    serialisedPizza = serialisedPizza.trim();
    String[] pizzaTokens = serialisedPizza.split(";");

    assertPizzaTokenLength(pizzaTokens);

    SimplePizza pizza = new SimplePizza(parsePizzaId(pizzaTokens));
    pizza.setPrice(parsePizzaPrice(pizzaTokens));
    pizza.setSize(parseSize(pizzaTokens));
    parseToppings(pizzaTokens).forEach(pizza::addTopping);
    return pizza;
  }

  private void assertPizzaTokenLength(String[] pizzaTokens) throws WrongOrderFormatException {
    if (pizzaTokens.length < 3 || pizzaTokens.length > 9) {
      throw new WrongOrderFormatException("Expected 3 to 9 entries but got " + pizzaTokens.length);
    }
  }

  public int parsePizzaId(String[] tokinisedPizza) throws WrongOrderFormatException {
    assertPizzaTokenLength(tokinisedPizza);
    try {
      return Integer.parseInt(tokinisedPizza[0]);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException(String.format("Invalid pizzaId: %s", tokinisedPizza[0]),
          e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no pizzaId set", e);
    }
  }

  public int parsePizzaPrice(String[] tokinisedPizza) throws WrongOrderFormatException {
    assertPizzaTokenLength(tokinisedPizza);
    try {
      return Integer.parseInt(tokinisedPizza[1]);
    } catch (NumberFormatException e) {
      throw new WrongOrderFormatException(String.format("Invalid price: %s", tokinisedPizza[1]), e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no price set", e);
    }
  }

  public PizzaSize parseSize(String[] tokinisedPizza) throws WrongOrderFormatException {
    assertPizzaTokenLength(tokinisedPizza);
    try {
      return PizzaSize.valueOf(tokinisedPizza[2]);
    } catch (IllegalArgumentException e) {
      throw new WrongOrderFormatException(String.format("Invalid size: %s", tokinisedPizza[1]), e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no size set", e);
    }
  }

  public List<Topping> parseToppings(String[] tokinisedPizza) throws WrongOrderFormatException {
    assertPizzaTokenLength(tokinisedPizza);
    try {
      List<Topping> toppingList = new ArrayList<>();
      for (String topping : Arrays.copyOfRange(tokinisedPizza, 3, tokinisedPizza.length)) {
        toppingList.add(parseTopping(topping));
      }
      return toppingList;
    } catch (IllegalArgumentException e) {
      throw new WrongOrderFormatException(String.format("Invalid size: %s", tokinisedPizza[1]), e);
    } catch (IndexOutOfBoundsException e) {
      throw new WrongOrderFormatException("no size set", e);
    }
  }

  public Topping parseTopping(String topping) throws WrongOrderFormatException {
    try {
      return Topping.valueOf(topping);
    } catch (IllegalArgumentException e) {
      throw new WrongOrderFormatException(String.format("Invalid topping: %s", topping), e);
    }
  }
}
