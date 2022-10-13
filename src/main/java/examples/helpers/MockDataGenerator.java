package examples.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * MockDataGenerator to generate mock data for programming examples.
 */
public class MockDataGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MockDataGenerator.class);

  /**
   * Generates some mock data and returns it as a List of Contacts.
   *
   * @return List of Contacts
   */
  public static List<Contact> getContactList() {
    List<Contact> contactList = new ArrayList<>();
    Contact contactHugo = new Contact("Hugo", "07131 / 23 45 678");
    Contact contactMarie = new Contact("Marie", "07131 / 23 45 678");
    contactList.add(contactHugo);
    contactList.add(contactMarie);
    Contact contactErna = new Contact("Erna", "07131 / 777 666");
    Contact contactEgon = new Contact("Egon", "07131 / 777 666");
    contactList.add(contactErna);
    contactList.add(contactEgon);
    Contact contactPaul = new Contact("Paul", "07131 / 98 76 54");
    Contact contactPaula = new Contact("Paula", "07131 / 98 76 54");
    contactList.add(contactPaul);
    contactList.add(contactPaula);

    return contactList;
  }
}
