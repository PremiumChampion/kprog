package prog.bonus.exercise.checklistservice;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a user. This class is immutable.
 */
public final class Token implements Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Token.class);

  private final String content;
  private final LocalDateTime creationTime;

  public Token(final String content) {
    this.content = content;
    this.creationTime = LocalDateTime.now();
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Token)) {
      return false;
    }

    Token token = (Token) o;

    if (!getContent().equals(token.getContent())) {
      return false;
    }
    return getCreationTime().equals(token.getCreationTime());
  }

  @Override
  public int hashCode() {
    int result = getContent().hashCode();
    result = 31 * result + getCreationTime().hashCode();
    return result;
  }
}
