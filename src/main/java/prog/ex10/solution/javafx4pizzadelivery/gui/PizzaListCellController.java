package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnEditEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnRemoveEventHandler;

/**
 * class PizzaListCellController.
 */
public class PizzaListCellController implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaListCellController.class);
  @FXML
  public Label pizzaSizeLabel;
  @FXML
  public Label toppingCountLabel;
  @FXML
  public Button changePizzaButton;
  @FXML
  public Button removePizzaButton;
  private Property<Pizza> pizza;
  private OnEditEventHandler<Pizza> onEditEventHandler;
  private OnRemoveEventHandler<Pizza> onRemoveEventHandler;

  /**
   * Constructor.
   */
  public PizzaListCellController() {
    this.pizza = new SimpleObjectProperty<>(null);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.pizza.addListener(this::pizzaChanged);
    this.removePizzaButton.setOnAction(this::onRemovePizza);
    this.changePizzaButton.setOnAction(this::changePizza);
  }

  /**
   * change button press.
   *
   * @param actionEvent event.
   */
  private void changePizza(ActionEvent actionEvent) {
    if (this.onEditEventHandler != null) {
      this.onEditEventHandler.edit(this.pizza.getValue());
    }
  }

  /**
   * remove button press.
   *
   * @param actionEvent event.
   */
  private void onRemovePizza(ActionEvent actionEvent) {
    if (this.onRemoveEventHandler != null) {
      this.onRemoveEventHandler.remove(this.pizza.getValue());
    }
  }

  /**
   * pizzaChanged listener.
   *
   * @param observable stuff.
   * @param oldValue   stuff.
   * @param newValue   stuff.
   */
  public void pizzaChanged(ObservableValue<? extends Pizza> observable, Pizza oldValue,
      Pizza newValue) {
    this.pizzaSizeLabel.setText(this.pizza.getValue().getSize().toString());
    this.toppingCountLabel.setText(
        String.format("%d Toppings", this.pizza.getValue().getToppings().size()));
  }

  /**
   * getter.
   *
   * @return pizza.
   */
  public Pizza getPizza() {
    return pizza.getValue();
  }

  /**
   * setter.
   *
   * @param pizza pizza.
   */
  public void setPizza(Pizza pizza) {
    this.pizza.setValue(pizza);
  }

  /**
   * getter.
   *
   * @return onEditEventHandler.
   */
  public OnEditEventHandler<Pizza> getOnEditEventHandler() {
    return onEditEventHandler;
  }

  /**
   * setter.
   *
   * @param onEditEventHandler onEditEventHandler.
   */
  public void setOnEditEventHandler(
      OnEditEventHandler<Pizza> onEditEventHandler) {
    this.onEditEventHandler = onEditEventHandler;
  }

  /**
   * getter.
   *
   * @return onRemoveEventHandler
   */
  public OnRemoveEventHandler<Pizza> getOnRemoveEventHandler() {
    return onRemoveEventHandler;
  }

  /**
   * setter.
   *
   * @param onRemoveEventHandler onRemoveEventHandler.
   */
  public void setOnRemoveEventHandler(
      OnRemoveEventHandler<Pizza> onRemoveEventHandler) {
    this.onRemoveEventHandler = onRemoveEventHandler;
  }
}
