package examples.javafx.observable;

import examples.helpers.Contact;
import examples.helpers.MockDataGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrator for a simple cell renderer for a ListView.
 */
public class DemoObservableList extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoObservableList.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane rootStackPane = new StackPane();
    ListView<Contact> contactListView = new ListView<>();
    ObservableList<Contact> contactObservableList =
            FXCollections.observableList(MockDataGenerator.getContactList());
    contactListView.setItems(contactObservableList);
    rootStackPane.getChildren().add(contactListView);
    primaryStage.setScene(new Scene(rootStackPane, 600, 300));
    primaryStage.show();
  }
}
