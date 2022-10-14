package examples.helpers;

/**
 * Simple contact for demo purposes containing a name and a phone number.
 */
public class Contact {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Contact.class);
  private static int idCounter;
  private int id;
  private String name;
  private String phoneNumber;

  /**
   * Constructor.
   *
   * @param name        name of the contact. Must contain at minimum two readable characters.
   * @param phoneNumber phone number of the contact. Must contain at minimum two readable numbers
   */
  public Contact(final String name, final String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    id = ++idCounter;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public String toString() {
    return "Contact{"
            + "id=" + id + ", name='" + name + '\''
            + ", phoneNumber='" + phoneNumber + '\'' + '}';
  }
}
