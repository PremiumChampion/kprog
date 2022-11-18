package examples.designpattern.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implementation of the observable part of the observer pattern using PropertyChangeSupport.
 */
public class PropertyChangeObservablePerson implements Person {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PropertyChangeObservablePerson.class);
  private final PropertyChangeSupport support;
  private String firstName;
  private String lastName;

  /**
   * Constructor.
   *
   * @param firstName first name of the person
   * @param lastName last name of the person
   */
  public PropertyChangeObservablePerson(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(final String firstName) {
    String oldFirstName = this.firstName;
    this.firstName = firstName;
    support.firePropertyChange("firstName", oldFirstName, firstName);
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(final String lastName) {
    String oldLastName = this.lastName;
    this.lastName = lastName;
    support.firePropertyChange("lastName", oldLastName, lastName);
  }

  @Override
  public String toString() {
    return "PropertyChangeObservablePerson{firstName='"
            + firstName + '\''
            + ", lastName='"
            + lastName + '\'' + '}';
  }
}
