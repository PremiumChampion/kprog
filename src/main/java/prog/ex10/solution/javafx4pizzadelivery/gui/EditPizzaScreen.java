package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.TooManyToppingsException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;

/**
 * JavaFX component to edit a pizza configuration.
 */
public class EditPizzaScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);
  private final Label pizzaSizeLabel;
  private final Label priceLabel;
  private final ChoiceBox<Topping> toppingChoiceBox;
  private final Button addToppingButton;
  private final ListView<Topping> toppingsOnPizzaListView;
  private final PizzaDeliveryService service;
  private final int orderId;
  private final int pizzaId;
  private final ObservableList<Topping> selectedToppings;
  private final Pizza pizza;
  private final Button finishButton;

  /**
   * Comment.
   *
   * @param service service.
   * @param orderId order.
   * @param pizzaId pizza.
   */
  public EditPizzaScreen(PizzaDeliveryService service, final int orderId, int pizzaId) {
    this.service = service;
    this.orderId = orderId;
    this.pizzaId = pizzaId;
    this.pizza = service.getOrder(orderId).getPizzaList().stream()
        .filter(p -> p.getPizzaId() == pizzaId).findFirst().orElse(null);
    PizzaSize size = pizza.getSize();

    this.pizzaSizeLabel = new Label();
    this.pizzaSizeLabel.setId("pizzaSizeLabel");
    this.pizzaSizeLabel.setText("Größe: " + size);

    this.priceLabel = new Label();
    this.priceLabel.setId("priceLabel");

    this.toppingChoiceBox = new ChoiceBox<>();
    this.toppingChoiceBox.setId("toppingChoiceBox");
    this.toppingChoiceBox.setItems(FXCollections.observableList(List.of(Topping.values())));

    this.addToppingButton = new Button();
    this.addToppingButton.setText("Topping hinzufügen");
    this.addToppingButton.setId("addToppingButton");
    this.addToppingButton.setOnAction(this::onAddTopping);

    this.toppingsOnPizzaListView = new ListView<>();
    this.toppingsOnPizzaListView.setId("toppingsOnPizzaListView");
    this.selectedToppings = FXCollections.observableList(new ArrayList<>(7));
    this.toppingsOnPizzaListView.setCellFactory(studentListView -> {
      ToppingListCell toppingListCell = new ToppingListCell(this.service);
      toppingListCell.setOnRemoveHandler(this::onRemoveTopping);
      return toppingListCell;
    });
    this.toppingsOnPizzaListView.setItems(selectedToppings);

    this.finishButton = new Button();
    this.finishButton.setId("finishButton");
    this.finishButton.setText("Bestellung abschließen");
    this.finishButton.setOnAction(this::onFinishOrder);

    this.getChildren()
        .addAll(this.pizzaSizeLabel, this.priceLabel, this.toppingChoiceBox, this.addToppingButton,
            this.toppingsOnPizzaListView, this.finishButton);

    this.update();
  }

  /**
   * update ui.
   */
  private void update() {
    this.selectedToppings.remove(0, this.selectedToppings.size());
    this.selectedToppings.addAll(pizza.getToppings());
    this.priceLabel.setText("Preis: " + pizza.getPrice() + " ct");
  }

  /**
   * on remove handler.
   *
   * @param toppingToRemove topping to remove.
   */
  private void onRemoveTopping(Topping toppingToRemove) {
    this.service.removeTopping(this.pizzaId, toppingToRemove);
    this.update();
  }

  /**
   * on add handler.
   *
   * @param event event.
   */
  private void onAddTopping(ActionEvent event) {
    Topping toppingToAdd = this.toppingChoiceBox.getValue();
    if (toppingToAdd == null) {
      new ExceptionAlert(new Exception("Bitte zuerst ein Toppinng auswählen.")).show();
      return;
    }

    try {
      this.service.addTopping(pizzaId, toppingToAdd);
      this.update();
    } catch (TooManyToppingsException e) {
      new ExceptionAlert(e).show();
    }
  }

  /**
   * finish handler.
   *
   * @param event event.
   */
  private void onFinishOrder(ActionEvent event) {
  }
}
