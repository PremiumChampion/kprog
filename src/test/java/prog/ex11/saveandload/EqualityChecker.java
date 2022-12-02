package prog.ex11.saveandload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;

public class EqualityChecker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(EqualityChecker.class);

  public static boolean equals(Order o1, Order o2) {
    if (o1.getOrderId() != o2.getOrderId()) {
      logger.info("False because Orderid is different.");
      return false;
    }

    if (o1.getValue() != o2.getValue()) {
      logger.info("False because value is different.");
      return false;
    }

    return equalsPizzaList(o1.getPizzaList(), o2.getPizzaList());
  }

  private static boolean equalsPizzaList(final List<Pizza> pizzaList1,
                                        final List<Pizza> pizzaList2) {
    if (pizzaList1 == null && pizzaList2 == null) {
      return true;
    }

    if (pizzaList1 == null || pizzaList2 == null) {
      logger.info("False because one pizzalist is null.");
      return false;
    }

    if (pizzaList1.size() != pizzaList2.size()) {
      logger.info("False because pizzalists have different sizes.");

      return false;
    }

    Map<Integer, Pizza> pizza1Map = new HashMap<>();
    Map<Integer, Pizza> pizza2Map = new HashMap<>();

    for (Pizza pizza : pizzaList1) {
      pizza1Map.put(pizza.getPizzaId(), pizza);
    }

    for (Pizza pizza : pizzaList2) {
      pizza2Map.put(pizza.getPizzaId(), pizza);
    }

    Set<Integer> keyset = pizza1Map.keySet();

    for (Integer id : keyset) {
      if (! equals(pizza1Map.get(id), pizza2Map.get(id))) {
        return false;
      }
    }

    return true;
  }

  private static boolean equals(final Pizza pizza1, final Pizza pizza2) {
    if (pizza1.getPizzaId() != pizza2.getPizzaId()){
      logger.info("False because Pizzaid is different.");
      return false;
    }

    if (pizza1.getPrice() != pizza2.getPrice()) {
      logger.info("False because pizzaprice is different.");
      return false;
    }

    if (pizza1.getSize() != pizza2.getSize()) {
      logger.info("False because pizzasize is different.");

      return false;
    }

    return equals(pizza1.getToppings(), pizza2.getToppings());
  }

  private static boolean equals(final List<Topping> toppings1, final List<Topping> toppings2) {
    if (toppings1.size() != toppings2.size()) {
      logger.info("False because number of toppings is different.");
      return false;
    }

    Map<Topping, Integer> map1 = new HashMap<>();
    Map<Topping, Integer> map2 = new HashMap<>();

    for (Topping topping : toppings1) {
      addTopping(map1, topping);
    }

    for (Topping topping : toppings2) {
      addTopping(map2, topping);
    }

    return map1.equals(map2);
  }

  private static void addTopping(final Map<Topping, Integer> map, final Topping topping) {
    if (!map.containsKey(topping)){
      map.put(topping, 1);
    } else {
      int counter = map.get(topping);
      map.put(topping, ++counter);
    }
  }
}
