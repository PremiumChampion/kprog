package examples.javafx.listcell;

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
public class DemoListCell extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoListCell.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane rootStackPane = new StackPane();
    ListView<Contact> contactListView = new ListView<>();
    ObservableList<Contact> contactObservableList =
        FXCollections.observableList(MockDataGenerator.getContactList());
    contactListView.setItems(contactObservableList);
    contactListView.setCellFactory(list -> new ContactListCell(contactObservableList));
    rootStackPane.getChildren().add(contactListView);
    primaryStage.setScene(new Scene(rootStackPane, 600, 300));
    primaryStage.show();
  }

  class ContactListCell extends ListCell<Contact> {
    private final ObservableList<Contact> contactList;

    public ContactListCell(final ObservableList<Contact> contactList) {
      this.contactList = contactList;
    }

    @Override
    protected void updateItem(final Contact item, final boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        VBox verticalBox = new VBox();
        Label nameLabel = new Label(item.getName());
        Label phoneNumberLabel = new Label(item.getPhoneNumber());
        verticalBox.getChildren().addAll(nameLabel, phoneNumberLabel);
        Button removeButton = new Button("remove");
        removeButton.setOnAction((event -> contactList.remove(item)));
        Pane spacer = new Pane();
        spacer.setMinSize(10, 1);
        HBox horizontalBox = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        horizontalBox.getChildren().addAll(verticalBox, spacer, removeButton);
        setGraphic(horizontalBox);
      }
    }
  }

}
