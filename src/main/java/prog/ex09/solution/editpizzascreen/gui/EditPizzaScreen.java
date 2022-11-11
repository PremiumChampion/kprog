package prog.ex09.solution.editpizzascreen.gui;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.ListCellRenderer;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

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
  private final ObservableList<Topping> toppings;
  private final ObservableList<Topping> selectedToppings;
  private final Pizza pizza;
  private final Button finishButton;


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
    this.priceLabel.setText("Preis: " + pizza.getPrice() + " ct");

    this.toppings = FXCollections.observableList(List.of(Topping.values()));

    this.toppingChoiceBox = new ChoiceBox<>();
    this.toppingChoiceBox.setId("toppingChoiceBox");
    this.toppingChoiceBox.setItems(this.toppings);

    this.addToppingButton = new Button();
    this.addToppingButton.setText("Topping hinzufügen");
    this.addToppingButton.setId("addToppingButton");
    this.addToppingButton.setOnAction(this::onAddTopping);

    this.toppingsOnPizzaListView = new ListView<>();
    this.toppingsOnPizzaListView.setId("toppingsOnPizzaListView");
    this.selectedToppings = FXCollections.observableList(new ArrayList<>());
    this.selectedToppings.addAll(pizza.getToppings());
    this.toppingsOnPizzaListView.setCellFactory(studentListView -> {
      ToppingListCell toppingListCell = new ToppingListCell(this.service);
      toppingListCell.setOnRemoveHandler(this::onRemoveTopping);
      return toppingListCell;
    });
    toppingsOnPizzaListView.setItems(selectedToppings);

    this.finishButton = new Button();
    this.finishButton.setId("finishButton");
    this.finishButton.setText("Bestellung abschließen");
    this.finishButton.setOnAction(this::onFinishOrder);

    this.getChildren()
        .addAll(this.pizzaSizeLabel, this.priceLabel, this.toppingChoiceBox, this.addToppingButton,
            this.toppingsOnPizzaListView, this.finishButton);
  }

  private void onRemoveTopping(Topping toppingToRemove) {
    this.selectedToppings.removeAll(pizza.getToppings());
    this.service.removeTopping(this.pizzaId, toppingToRemove);
    this.selectedToppings.addAll(pizza.getToppings());
  }

  private void onAddTopping(ActionEvent event) {
    Topping toppingToAdd = this.toppingChoiceBox.getValue();
    if (toppingToAdd == null) {
      return;
    }
    this.selectedToppings.removeAll(pizza.getToppings());
    try {
      this.service.addTopping(pizzaId, toppingToAdd);
    } catch (TooManyToppingsException e) {
      logger.info("Can not add topping");
    }
    this.selectedToppings.addAll(pizza.getToppings());
    this.priceLabel.setText("Preis: " + pizza.getPrice() + " ct");
  }

  private void onFinishOrder(ActionEvent event){
    Platform.exit();
  }
}
