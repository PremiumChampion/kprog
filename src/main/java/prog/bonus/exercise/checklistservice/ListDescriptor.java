package prog.bonus.exercise.checklistservice;

import java.io.Serializable;

/**
 * A list descriptor. This class is immutable.
 */
public final class ListDescriptor implements Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ListDescriptor.class);

  private final Token owner;
  private final long id;
  private final String name;
  private final int numberOfEntries;

  /**
   * Creates a new list descriptor.
   *
   * @param owner           the owner of the list
   * @param id              the id of the list
   * @param name            the name of the list
   * @param numberOfEntries the number of entries in the list
   */
  public ListDescriptor(final Token owner, final long id, final String name, int numberOfEntries) {
    this.owner = owner;
    this.id = id;
    this.name = name;
    this.numberOfEntries = numberOfEntries;
  }

  public Token getOwner() {
    return owner;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getNumberOfEntries() {
    return numberOfEntries;
  }
}
