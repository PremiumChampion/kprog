package examples.designpattern.observer;

/**
 * Demonstrates differences of Using Observer / Observable or PropertyChangeSupport /
 * PropertyChangeListener.
 *
 */
public class DemoWithObservers {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoWithObservers.class);

  /**
   * Main method.
   *
   * @param args No args are expected
   */
  public static void main(String[] args) {
    DepreactedObservablePerson depreactedObservablePerson =
            new DepreactedObservablePerson("Victor", "Hugo");
    DeprecatedPersonObserver deprecatedPersonObserver = new DeprecatedPersonObserver();
    depreactedObservablePerson.addObserver(deprecatedPersonObserver);
    depreactedObservablePerson.setFirstName("Alexander");

    PropertyChangeObservablePerson propertyChangeObservablePerson =
            new PropertyChangeObservablePerson("Alexandre", "Dumas");
    ProperyChangePersonObserver properyChangePersonObserver = new ProperyChangePersonObserver();
    propertyChangeObservablePerson.addPropertyChangeListener(properyChangePersonObserver);
    propertyChangeObservablePerson.setFirstName("Viktor");
  }
}
