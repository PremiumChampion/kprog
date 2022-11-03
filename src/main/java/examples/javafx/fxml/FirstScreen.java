package examples.javafx.fxml;


import examples.helpers.Contact;
import examples.helpers.MockDataGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Demonstrates a simple javafx fxml based component.
 */
public class FirstScreen extends VBox implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FirstScreen.class);

  @FXML
  private ListView<Contact> contactListView;
  private ObservableList<Contact> contactObservableList;

  /**
   * Constructor of FirstScreen.
   */
  public FirstScreen() {
    contactObservableList = FXCollections.observableList(MockDataGenerator.getContactList());

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FirstScreen.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
