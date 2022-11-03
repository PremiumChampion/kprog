package examples.javafx.fxml;


import examples.helpers.Contact;
import examples.helpers.MockDataGenerator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Demonstrates a simple javafx fxml based component.
 */
public class SecondScreen extends VBox implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SecondScreen.class);

  @FXML
  private ListView<Contact> contactListView;
  private ObservableList<Contact> contactObservableList;

  /**
   * Constructor of FirstScreen.
   */
  public SecondScreen() {
    contactObservableList = FXCollections.observableList(MockDataGenerator.getContactList());
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    contactListView.setItems(contactObservableList);
    contactListView.getSelectionModel()
            .selectedItemProperty()
            .addListener((changed, oldVal, newVal) -> onSelectionDialContact(newVal));
  }

  private void onSelectionDialContact(final Contact newVal) {
    logger.info("Try dialing {} with number {}", newVal.getName(), newVal.getPhoneNumber());
  }


}
