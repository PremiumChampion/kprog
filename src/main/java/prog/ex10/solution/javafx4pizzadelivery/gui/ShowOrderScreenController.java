package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnAddEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnCancelOrderEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnEditEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnFinishOrderEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnRemoveEventHandler;

/**
 * class ShowOrderScreenController.
 */
public class ShowOrderScreenController implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreenController.class);
  @FXML
  public Label orderIdLabel;
  @FXML
  public Label orderPriceLabel;
  @FXML
  public ChoiceBox<PizzaSize> pizzaSizeChoiceBox;
  @FXML
  public Button addPizzaButton;
  @FXML
  public ListView<Pizza> pizzaInOrderList;
  @FXML
  public Button cancelOrderButton;
  @FXML
  public Button onSubmitOrderButton;

  private OnAddEventHandler onAddEventHandler;
  private OnCancelOrderEventHandler onCancelOrderEventHandler;
  private OnFinishOrderEventHandler onFinishOrderEventHandler;
  private OnRemoveEventHandler<Pizza> onRemoveEventHandler;
  private OnEditEventHandler<Pizza> onEditEventHandler;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addPizzaButton.setOnAction(this::onAddPizza);
    cancelOrderButton.setOnAction(this::onCancelOrder);
    onSubmitOrderButton.setOnAction(this::onSubmitOrder);
    pizzaSizeChoiceBox.getItems().addAll(PizzaSize.values());
    pizzaSizeChoiceBox.getSelectionModel().selectFirst();
    pizzaInOrderList.setCellFactory(view -> {
      PizzaListCell pizzaListCell = new PizzaListCell();
      pizzaListCell.setOnEditEventHandler(this::onEditPizza);
      pizzaListCell.setOnRemoveEventHandler(this::onRemovePizza);
      return pizzaListCell;
    });
  }

  /**
   * stuff.
   *
   * @param pizza param stuff.
   */
  private void onRemovePizza(Pizza pizza) {
    if (this.onRemoveEventHandler != null) {
      this.onRemoveEventHandler.remove(pizza);
    }
  }

  /**
   * stuff.
   *
   * @param pizza param stuff.
   */
  private void onEditPizza(Pizza pizza) {
    if (this.onEditEventHandler != null) {
      this.onEditEventHandler.edit(pizza);
    }
  }

  /**
   * stuff.
   *
   * @param actionEvent param stuff.
   */
  private void onSubmitOrder(ActionEvent actionEvent) {
    if (this.onFinishOrderEventHandler != null) {
      this.onFinishOrderEventHandler.finish();
    }
  }

  /**
   * stuff.
   *
   * @param actionEvent param stuff.
   */
  private void onCancelOrder(ActionEvent actionEvent) {
    if (this.onCancelOrderEventHandler != null) {
      this.onCancelOrderEventHandler.cancel();
    }
  }

  /**
   * stuff.
   *
   * @param actionEvent param stuff.
   */
  private void onAddPizza(ActionEvent actionEvent) {
    if (this.onAddEventHandler != null) {
      this.onAddEventHandler.add();
    }
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public ObservableList<Pizza> getPizzaList() {
    return this.pizzaInOrderList.getItems();
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public StringProperty getOrderIdLabelTextProperty() {
    return orderIdLabel.textProperty();
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public StringProperty getOrderPriceLabelTextProperty() {
    return orderPriceLabel.textProperty();
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public PizzaSize getSelectedPizzaSize() {
    return pizzaSizeChoiceBox.getValue();
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public OnAddEventHandler getOnAddEventHandler() {
    return onAddEventHandler;
  }

  /**
   * stuff.
   *
   * @param onAddEventHandler param stuff.
   */
  public void setOnAddEventHandler(
      OnAddEventHandler onAddEventHandler) {
    this.onAddEventHandler = onAddEventHandler;
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public OnCancelOrderEventHandler getOnCancelOrderEventHandler() {
    return onCancelOrderEventHandler;
  }

  /**
   * stuff.
   *
   * @param onCancelOrderEventHandler param stuff.
   */
  public void setOnCancelOrderEventHandler(
      OnCancelOrderEventHandler onCancelOrderEventHandler) {
    this.onCancelOrderEventHandler = onCancelOrderEventHandler;
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public OnFinishOrderEventHandler getOnFinishOrderEventHandler() {
    return onFinishOrderEventHandler;
  }

  /**
   * stuff.
   *
   * @param onFinishOrderEventHandler param stuff.
   */
  public void setOnFinishOrderEventHandler(
      OnFinishOrderEventHandler onFinishOrderEventHandler) {
    this.onFinishOrderEventHandler = onFinishOrderEventHandler;
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public OnRemoveEventHandler<Pizza> getOnRemoveEventHandler() {
    return onRemoveEventHandler;
  }

  /**
   * stuff.
   *
   * @param onRemoveEventHandler param stuff.
   */
  public void setOnRemoveEventHandler(
      OnRemoveEventHandler<Pizza> onRemoveEventHandler) {
    this.onRemoveEventHandler = onRemoveEventHandler;
  }

  /**
   * stuff.
   *
   * @return param stuff.
   */
  public OnEditEventHandler<Pizza> getOnEditEventHandler() {
    return onEditEventHandler;
  }

  /**
   * stuff.
   *
   * @param onEditEventHandler param stuff.
   */
  public void setOnEditEventHandler(
      OnEditEventHandler<Pizza> onEditEventHandler) {
    this.onEditEventHandler = onEditEventHandler;
  }
}
