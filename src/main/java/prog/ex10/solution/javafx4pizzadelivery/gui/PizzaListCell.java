package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnEditEventHandler;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnRemoveEventHandler;

/**
 * class PizzaListCell.
 */
public class PizzaListCell extends ListCell<Pizza> implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaListCell.class);
  private PizzaListCellModel model;
  private final Node ui;
  @FXML
  public Label pizzaSizeLabel;
  @FXML
  public Label toppingCountLabel;
  @FXML
  public Button changePizzaButton;
  @FXML
  public Button removePizzaButton;
  public OnRemoveEventHandler<Pizza> onRemoveEventHandler;
  public OnEditEventHandler<Pizza> onEditEventHandler;

  /**
   * Constructor.
   */
  public PizzaListCell() {
    model = new PizzaListCellModel();
    model.pizzaProperty().addListener(this::pizzaChanged);
    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
          getClass().getClassLoader().getResource("ex10/PizzaListCell.fxml"),
          "PizzaListCell.fxml resource not found."));
      loader.setController(this);
      ui = loader.load();

    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private void pizzaChanged(Observable observable) {
    Pizza pizza = model.getPizza();
    if (pizza == null) {
      return;
    }
    model.setToppingCount(pizza.getToppings().size());
    model.setSize(pizza.getSize());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    changePizzaButton.setOnAction(this::editPizza);
    removePizzaButton.setOnAction(this::removePizza);
    pizzaSizeLabel.textProperty().bind(model.sizeProperty().asString("Größe: %s"));
    toppingCountLabel.textProperty().bind(model.toppingCountProperty().asString("Toppings %d"));
  }

  private void editPizza(ActionEvent actionEvent) {
    if (onEditEventHandler == null) {
      return;
    }
    onEditEventHandler.edit(model.getPizza());
  }

  private void removePizza(ActionEvent actionEvent) {
    if (onRemoveEventHandler == null) {
      return;
    }
    onRemoveEventHandler.remove(model.getPizza());
  }

  @Override
  protected void updateItem(Pizza pizza, boolean empty) {
    super.updateItem(pizza, empty);

    if (pizza == null || empty) {
      setGraphic(null);
      return;
    }

    model.setPizza(null);
    model.setPizza(pizza);

    setGraphic(ui);
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
  public void setOnRemoveEventHandler(OnRemoveEventHandler<Pizza> onRemoveEventHandler) {
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
  public void setOnEditEventHandler(OnEditEventHandler<Pizza> onEditEventHandler) {
    this.onEditEventHandler = onEditEventHandler;
  }
}
