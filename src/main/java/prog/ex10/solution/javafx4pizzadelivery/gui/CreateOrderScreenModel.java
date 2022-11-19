package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;

/**
 * class CreateOrderScreenModel.
 */
public class CreateOrderScreenModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreenModel.class);
  private final IntegerProperty orderId = new SimpleIntegerProperty();
  private final ObjectProperty<PizzaDeliveryService> deliveryService = new SimpleObjectProperty<>();

  //region getter/setter

  /**
   * stuff.
   *
   * @return stuff.
   */
  public int getOrderId() {
    return orderId.get();
  }

  /**
   * stuff.
   *
   * @return stuff.
   */
  public IntegerProperty orderIdProperty() {
    return orderId;
  }

  /**
   * stuff.
   *
   * @param orderId stuff.
   */
  public void setOrderId(int orderId) {
    this.orderId.set(orderId);
  }


  /**
   * stuff.
   *
   * @return stuff.
   */
  public PizzaDeliveryService getDeliveryService() {
    return deliveryService.get();
  }

  /**
   * stuff.
   *
   * @return stuff.
   */
  public ObjectProperty<PizzaDeliveryService> deliveryServiceProperty() {
    return deliveryService;
  }

  /**
   * stuff.
   *
   * @param deliveryService stuff.
   */
  public void setDeliveryService(
      PizzaDeliveryService deliveryService) {
    this.deliveryService.set(deliveryService);
  }
  //endregion
}
