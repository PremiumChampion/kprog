package prog.bonus.exercise.checklistservice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

/**
 * Represents an entry of a checklist. This class is immutable.
 */
public final class Entry implements Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Entry.class);

  private final long listId;
  private final long id;
  private final LocalDateTime created;
  private final String content;

  private final boolean checked;
  private final List<Long> tagList;

  /**
   * Creates a new entry.
   *
   * @param listId  the id of the list this entry belongs to
   * @param id      the id of the entry
   * @param created the creation time of the entry
   * @param content the content of the entry
   * @param checked whether the entry is checked
   * @param tagList the list of tags
   */
  public Entry(final long listId, final long id, final LocalDateTime created,
      final String content, final boolean checked, final List<Long> tagList) {
    this.listId = listId;
    this.id = id;
    this.created = created;
    this.content = content;
    this.checked = checked;
    this.tagList = tagList;
  }

  public long getListId() {
    return listId;
  }

  public long getId() {
    return id;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public String getContent() {
    return content;
  }

  public List<Long> getTagList() {
    return tagList;
  }

  public boolean isChecked() {
    return checked;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Entry.class.getSimpleName() + "[", "]")
        .add("listId=" + listId)
        .add("id=" + id)
        .add("created=" + created)
        .add("content='" + content + "'")
        .add("tagList=" + tagList)
        .toString();
  }
}
