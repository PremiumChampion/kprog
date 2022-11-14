package examples.designpattern.observer;

/**
 * Simple representation of a person having a first name and a last name.
 */
public interface Person {
  String getFirstName();

  void setFirstName(String firstName);

  String getLastName();

  void setLastName(String lastName);
}

