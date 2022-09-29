package examples.ch14.newsfeedv1t;

public class AdressBookDemoLauncher {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AdressBookDemoLauncher.class);

  public static void main(String[] args) {
    AddressBookDemo demo = new AddressBookDemo();
    demo.showInterface();
  }
}
