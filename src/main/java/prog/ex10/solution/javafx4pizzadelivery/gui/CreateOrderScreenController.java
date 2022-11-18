package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import prog.ex10.solution.javafx4pizzadelivery.gui.events.OnCreateOrderEventHandler;

/**
 * class CreateOrderScreenController.
 */
public class CreateOrderScreenController implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreenController.class);
  @FXML
  public Button createOrderButton;
  private OnCreateOrderEventHandler onCreateOrder;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    this.createOrderButton.setOnAction(this::onCreateOrder);
  }

  /**
   * event handler for the create order button.
   *
   * @param e action event.
   */
  private void onCreateOrder(ActionEvent e) {
    if (this.onCreateOrder != null) {
      this.onCreateOrder.create();
    }
  }

  /**
   * getter.
   *
   * @return create order handler.
   */
  public OnCreateOrderEventHandler getOnCreateOrder() {
    return onCreateOrder;
  }

  /**
   * setter.
   *
   * @param onCreateOrder setter.
   */
  public void setOnCreateOrder(
      OnCreateOrderEventHandler onCreateOrder) {
    this.onCreateOrder = onCreateOrder;
  }

}
