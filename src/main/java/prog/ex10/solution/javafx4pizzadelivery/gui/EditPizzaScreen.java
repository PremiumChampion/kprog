package prog.ex10.solution.javafx4pizzadelivery.gui;

import examples.javafx.modal.ExceptionAlert;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.Observable;
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
  private final PizzaDeliveryScreenController screenController;
  private final EditPizzaScreenModel model;
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

    this.model = new EditPizzaScreenModel();

    this.model.pizzaProperty().addListener(this::pizzaPropertyChanged);

    this.model.orderIdProperty()
        .bindBidirectional((IntegerProperty) store.getAttribute(SingletonAttributeStore.ORDER_ID));
    this.model.pizzaIdProperty()
        .bindBidirectional((IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID));

    this.model.pizzaIdProperty().addListener(this::updateIds);
    this.model.orderIdProperty().addListener(this::updateIds);

    this.pizzaSizeLabel = new Label();
    this.pizzaSizeLabel.setId("pizzaSizeLabel");
    this.pizzaSizeLabel.textProperty().bind(model.sizeProperty().asString("Größe %s"));

    this.priceLabel = new Label();
    this.priceLabel.setId("priceLabel");
    this.priceLabel.textProperty().bind(model.priceProperty().asString("Preis %dct"));

    this.toppingChoiceBox = new ChoiceBox<>();
    this.toppingChoiceBox.setId("toppingChoiceBox");
    this.toppingChoiceBox.setItems(FXCollections.observableList(List.of(Topping.values())));
    this.toppingChoiceBox.getSelectionModel().selectFirst();

    this.addToppingButton = new Button();
    this.addToppingButton.setText("Topping hinzufügen");
    this.addToppingButton.setId("addToppingButton");
    this.addToppingButton.setOnAction(this::onAddTopping);

    this.toppingsOnPizzaListView = new ListView<>();
    this.toppingsOnPizzaListView.setId("toppingsOnPizzaListView");
    this.toppingsOnPizzaListView.setItems(model.getSelectedToppings());
    this.toppingsOnPizzaListView.setCellFactory(studentListView -> {
      ToppingListCell toppingListCell = new ToppingListCell(this.service);
      toppingListCell.setOnRemoveHandler(this::onRemoveTopping);
      return toppingListCell;
    });

    this.finishButton = new Button();
    this.finishButton.setId("finishButton");
    this.finishButton.setText("Bestellung abschließen");
    this.finishButton.setOnAction(this::onFinishOrder);

    this.getChildren()
        .addAll(this.pizzaSizeLabel, this.priceLabel, this.toppingChoiceBox, this.addToppingButton,
            this.toppingsOnPizzaListView, this.finishButton);

    setPizzaFromService();
  }

  private void pizzaPropertyChanged(Observable observable) {
    updatePizza();
  }

  private void updateIds(Observable observable) {
    setPizzaFromService();
  }

  private void setPizzaFromService() {
    int pizzaId = model.getPizzaId();
    int orderId = model.getOrderId();

    model.setPizza(null);

    if (orderId == -1 || pizzaId == -1) {
      return;
    }

    Pizza pizza = this.service.getOrder(orderId).getPizzaList().stream()
        .filter(p -> p.getPizzaId() == pizzaId).findFirst().orElse(null);

    model.setPizza(pizza);
  }

  private void updatePizza() {
    Pizza pizza = model.getPizza();

    if (pizza == null) {
      return;
    }

    ObservableList<Topping> selectedToppings = model.getSelectedToppings();
    selectedToppings.clear();
    selectedToppings.addAll(pizza.getToppings());

    model.setSize(pizza.getSize());
    model.setPrice(pizza.getPrice());
  }


  /**
   * on remove handler.
   *
   * @param toppingToRemove topping to remove.
   */
  private void onRemoveTopping(Topping toppingToRemove) {
    Pizza pizza = model.getPizza();

    model.setPizza(null);

    if (pizza == null) {
      return;
    }

    this.service.removeTopping(pizza.getPizzaId(), toppingToRemove);
    updatePizza();
  }

  /**
   * on add handler.
   *
   * @param event event.
   */
  private void onAddTopping(ActionEvent event) {
    Pizza pizza = model.getPizza();

    model.setPizza(null);

    if (pizza == null) {
      return;
    }

    Topping toppingToAdd = this.toppingChoiceBox.getValue();
    if (toppingToAdd == null) {
      new ExceptionAlert(new Exception("Bitte zuerst ein Toppinng auswählen.")).show();
      return;
    }

    try {
      service.addTopping(pizza.getPizzaId(), toppingToAdd);
      model.setPizza(pizza);
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
      ((IntegerProperty) store.getAttribute(SingletonAttributeStore.PIZZA_ID)).setValue(-1);
      this.screenController.switchTo(EditPizzaScreen.SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      new ExceptionAlert(e).show();
    }
  }

}
