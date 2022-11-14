package examples.designpattern.observer;


import java.util.Observable;

/**
 * Demonstrates Usage of Observable.
 */
public class DepreactedObservablePerson extends Observable implements Person {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DepreactedObservablePerson.class);

  private String firstName;
  private String lastName;

  public DepreactedObservablePerson(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
    setChanged();
    notifyObservers(firstName);
  }

  @Override
  public void setLastName(final String lastName) {
    this.lastName = lastName;
    setChanged();
    notifyObservers(lastName);
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return "ObservablePerson{firstName='"
            + firstName + '\''
            + ", lastName='"
            + lastName + '\''
            + '}';
  }
}
