package examples.javafx.addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Simple and not optimized reimplementation of the AddressBook interface according to the swing
 * example in the book "Objects First with Java", chapter 14.
 */
public class AddressBookFxGui extends TabPane {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AddressBookFxGui.class);
  private final AddressBook book;
  private TextField searchInput;
  private ObservableList<ContactDetails> contactDetailsObservableList;
  private TextField nameTextField;
  private TextField phoneTextField;
  private TextArea addressTextArea;

  /**
   * Constructor.
   *
   * @param book AddressBook to be visualized and manipulated
   */
  public AddressBookFxGui(final AddressBook book) {
    this.book = book;
    Tab searchTab = new Tab("Search the Entries");
    getTabs().add(searchTab);
    addSearch(searchTab);
    Tab newEntryTab = new Tab("Enter New Details");
    getTabs().add(newEntryTab);
    addNewEntry(newEntryTab);
    Tab listTab = new Tab("List the Entries");
    getTabs().add(listTab);
    addListEntries(listTab);
  }

  private void addSearch(final Tab searchTab) {
    HBox searchLine = new HBox();
    searchLine.getChildren().add(new Label("Search"));
    searchInput = new TextField();
    searchInput.textProperty().addListener((e) -> updateSearch());
    searchLine.getChildren().add(searchInput);
    VBox searchBox = new VBox();
    searchBox.getChildren().add(searchLine);
    searchTab.setContent(searchBox);
    contactDetailsObservableList = FXCollections.observableArrayList();
    ListView<ContactDetails> contactListView = new ListView<ContactDetails>();
    contactListView.setItems(contactDetailsObservableList);
    searchBox.getChildren().add(contactListView);
  }

  void updateSearch() {
    ContactDetails[] result = book.search(searchInput.getText());
    contactDetailsObservableList.clear();
    contactDetailsObservableList.addAll(result);
  }

  private void addNewEntry(final Tab newEntryTab) {
    VBox addEntryBox = new VBox();
    addEntryBox.getChildren().add(new Label("Name"));
    nameTextField = new TextField();
    addEntryBox.getChildren().add(nameTextField);
    addEntryBox.getChildren().add(new Label("Phone"));
    phoneTextField = new TextField();
    addEntryBox.getChildren().add(phoneTextField);
    addEntryBox.getChildren().add(new Label("Address"));
    addressTextArea = new TextArea();
    addEntryBox.getChildren().add(addressTextArea);
    Button addButton = new Button("Add");
    addButton.setOnAction((e) -> addEntryToBook());
    Button clearButton = new Button("Clear");
    clearButton.setOnAction((e) -> clearEntryForm());
    HBox buttonBox = new HBox();
    buttonBox.getChildren().addAll(addButton, clearButton);
    addEntryBox.getChildren().add(buttonBox);
    newEntryTab.setContent(addEntryBox);
  }

  private void addEntryToBook() {
    ContactDetails contactDetails = new ContactDetails(
        nameTextField.getText(),
        phoneTextField.getText(),
        addressTextArea.getText()
    );
    book.addDetails(contactDetails);
    clearEntryForm();
  }

  private void clearEntryForm() {
    nameTextField.textProperty().setValue("");
    phoneTextField.textProperty().setValue("");
    addressTextArea.textProperty().setValue("");
  }

  private void addListEntries(final Tab listTab) {
    VBox listBox = new VBox();
    ObservableList<ContactDetails> completeContactDetails = FXCollections.observableArrayList();
    ListView<ContactDetails> contactDetailsListView = new ListView<>();
    contactDetailsListView.setItems(completeContactDetails);
    listBox.getChildren().add(contactDetailsListView);
    HBox buttonBox = new HBox();
    Button listButton = new Button("List");
    listButton.setOnAction((e) -> {
      completeContactDetails.clear();
      completeContactDetails.addAll(book.search(""));
    });
    Button clearButton = new Button("Clear");
    clearButton.setOnAction((e) -> completeContactDetails.clear());
    buttonBox.getChildren().addAll(listButton, clearButton);
    listBox.getChildren().add(buttonBox);
    listTab.setContent(listBox);
  }
}
