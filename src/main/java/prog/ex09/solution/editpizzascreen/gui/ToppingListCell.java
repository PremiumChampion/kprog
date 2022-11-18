package prog.ex09.solution.editpizzascreen.gui;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * class ToppingListCell.
 */
public class ToppingListCell extends ListCell<Topping> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ToppingListCell.class);
  private final Button removeButton;
  private final PizzaDeliveryService service;
  private OnRemoveEvent<Topping> onRemoveHandler;
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
      setGraphic(null);
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
  public void setOnRemoveHandler(OnRemoveEvent<Topping> onRemoveHandler) {
    this.onRemoveHandler = onRemoveHandler;
  }
}
