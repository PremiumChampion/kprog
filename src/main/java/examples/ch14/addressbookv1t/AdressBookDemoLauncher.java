package examples.ch14.addressbookv1t;

/**
 * Demo of an AddressBook with a textual interface.
 */
public class AdressBookDemoLauncher {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AdressBookDemoLauncher.class);

  public static void main(String[] args) {
    AddressBookDemo demo = new AddressBookDemo();
    demo.showInterface();
  }
}
