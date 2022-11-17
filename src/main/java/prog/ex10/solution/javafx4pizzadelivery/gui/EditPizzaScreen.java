package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
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
  private final IntegerProperty orderId;
  private final IntegerProperty pizzaId;
  private final ObservableList<Topping> selectedToppings;
  private final PizzaDeliveryScreenController screenController;
  private Pizza pizza;
  private final Button finishButton;
  public static final String SCREEN_NAME = "EditPizzaScreen";
  private final SingletonAttributeStore store;

  /**
   * stuff.
   *
   * @param screenController stuff.
   */
  public EditPizzaScreen(PizzaDeliveryScreenController screenController) {
    this.screenController = screenController;
    this.store = SingletonAttributeStore.getInstance();

    this.service = (PizzaDeliveryService) store.getAttribute(
        SingletonAttributeStore.PIZZA_DELIVERY_SERVICE);
    this.orderId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID);
    this.pizzaId = (IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID);

    this.pizzaId.addListener(this::updateData);
    this.orderId.addListener(this::updateData);

    this.pizzaSizeLabel = new Label();
    this.pizzaSizeLabel.setId("pizzaSizeLabel");

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
    if (!findPizza()) {
      return;
    }
    update();
  }

  /**
   * something changed.
   *
   * @param observable stuff.
   * @param oldValue   stuff.
   * @param newValue   stuff.
   */
  private void updateData(ObservableValue<? extends Number> observable, Number oldValue,
      Number newValue) {
    if (!findPizza()) {
      return;
    }
    this.update();
  }

  /**
   * finds and sets the pizza value.
   *
   * @return if the pizza was found.
   */
  private boolean findPizza() {
    if (this.orderId.getValue() == -1 || this.pizzaId.getValue() == -1) {
      return false;
    }
    pizza = this.service.getOrder(this.orderId.getValue()).getPizzaList().stream()
        .filter(p -> p.getPizzaId() == this.pizzaId.getValue()).findFirst().orElse(null);
    if (pizza == null) {
      return false;
    }
    return true;
  }

  /**
   * update ui.
   */
  private void update() {
    this.selectedToppings.clear();
    this.selectedToppings.addAll(pizza.getToppings());
    this.priceLabel.setText("Preis: " + pizza.getPrice() + " ct");
    this.pizzaSizeLabel.setText("Größe: " + pizza.getSize());
  }

  /**
   * on remove handler.
   *
   * @param toppingToRemove topping to remove.
   */
  private void onRemoveTopping(Topping toppingToRemove) {
    if (pizza == null) {
      return;
    }
    this.service.removeTopping(this.pizzaId.getValue(), toppingToRemove);
    this.update();
  }

  /**
   * on add handler.
   *
   * @param event event.
   */
  private void onAddTopping(ActionEvent event) {
    if (pizza == null) {
      return;
    }
    Topping toppingToAdd = this.toppingChoiceBox.getValue();
    if (toppingToAdd == null) {
      new ExceptionAlert(new Exception("Bitte zuerst ein Toppinng auswählen.")).show();
      return;
    }

    try {
      this.service.addTopping(pizzaId.getValue(), toppingToAdd);
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
    try {
      this.pizzaId.setValue(-1);
      this.screenController.switchTo(EditPizzaScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).show();
    }
  }

}
