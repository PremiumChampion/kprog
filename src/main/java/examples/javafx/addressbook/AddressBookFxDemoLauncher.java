package examples.javafx.addressbook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Demonstrates the AddressBook with a graphical user interface.
 */
public class AddressBookFxDemoLauncher extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AddressBookFxDemoLauncher.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    AddressBookDemo demo = new AddressBookDemo();
    AddressBook book = demo.getBook();
    primaryStage.setScene(new Scene(new AddressBookFxGui(book)));
    primaryStage.show();
  }
}
