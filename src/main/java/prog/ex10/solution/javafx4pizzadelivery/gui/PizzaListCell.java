package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnEditEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnRemoveEventHandler;

/**
 * class PizzaListCell.
 */
public class PizzaListCell extends ListCell<Pizza> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaListCell.class);
  private final FXMLLoader loader;
  private final PizzaListCellController controller;
  private final Node ui;

  private OnRemoveEventHandler<Pizza> onRemoveEventHandler;
  private OnEditEventHandler<Pizza> onEditEventHandler;

  /**
   * Constructor.
   */
  public PizzaListCell() {
    try {
      this.loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("PizzaListCell.fxml"),
          "PizzaListCell.fxml resource not found."));
      this.ui = loader.load();
      this.controller = loader.getController();
      controller.setOnEditEventHandler(this::onEditPizza);
      controller.setOnRemoveEventHandler(this::onRemovePizza);
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void updateItem(Pizza pizza, boolean empty) {
    super.updateItem(pizza, empty);
    if (pizza == null || empty) {
      setGraphic(null);
      return;
    }
    controller.setPizza(pizza);
    setGraphic(this.ui);
  }

  /**
   * getter.
   *
   * @return on remove handler.
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
   * event handler edit.
   *
   * @param pizza to edit.
   */
  private void onEditPizza(Pizza pizza) {
    if (this.onEditEventHandler != null) {
      this.onEditEventHandler.edit(pizza);
    }
  }

  /**
   * event handler remove.
   *
   * @param pizza to remove
   */
  private void onRemovePizza(Pizza pizza) {
    if (this.onRemoveEventHandler != null) {
      this.onRemoveEventHandler.remove(pizza);
    }
  }
}
