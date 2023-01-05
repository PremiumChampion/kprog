package prog.bonus.exercise.checklistservice;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * This class represents a tag. This class is immutable.
 */
public final class Tag implements Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Tag.class);

  private final long id;
  private final String name;

  public Tag(final long id, final String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .toString();
  }
}
