package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnRemoveEventHandler;

/**
 * class ToppingListCell.
 */
public class ToppingListCell extends ListCell<Topping> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ToppingListCell.class);
  private final Button removeButton;
  private final PizzaDeliveryService service;
  private OnRemoveEventHandler<Topping> onRemoveHandler;
  private Topping topping;

  /**
   * A list cell for a topping.
   *
   * @param service the pizza delivery service.
   */
  public ToppingListCell(PizzaDeliveryService service) {
    this.service = service;
    this.removeButton = new Button();
    removeButton.setOnAction(this::onRemoveButtonAction);
  }

  @Override
  protected void updateItem(Topping topping, boolean empty) {
    super.updateItem(topping, empty);
    this.topping = topping;

    if (topping == null || empty) {
      setGraphic(new HBox());
      setText(null);
      return;
    }

    removeButton.setId("remove-" + this.topping);
    removeButton.setText("Remove " + this.topping);
    setGraphic(removeButton);
  }

  /**
   * Comment.
   *
   * @param event event handler.
   */
  private void onRemoveButtonAction(ActionEvent event) {
    if (this.onRemoveHandler != null) {
      this.onRemoveHandler.remove(this.topping);
    }
  }

  /**
   * Comment.
   *
   * @param onRemoveHandler event handler.
   */
  public void setOnRemoveHandler(OnRemoveEventHandler<Topping> onRemoveHandler) {
    this.onRemoveHandler = onRemoveHandler;
  }
}
