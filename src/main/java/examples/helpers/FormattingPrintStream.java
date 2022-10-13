package examples.helpers;

import java.io.PrintStream;

/**
 * The FormattingPrintStream tries to limit the line length to a certain line limit. If a print
 * statement reaches the limit, a newline will be inserted as soon as possible.
 */
public class FormattingPrintStream {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FormattingPrintStream.class);

  private final PrintStream printStream;
  private final int lineLimit;
  private int lengthCounter;
  private String line;

  /**
   * Constructor.
   *
   * @param outputStream OutputStream to write on
   * @param lineLimit line length the formatter tries to match
   */
  public FormattingPrintStream(final PrintStream outputStream, final int lineLimit) {
    this.printStream = outputStream;
    this.lineLimit = lineLimit;
    line = "";
    lengthCounter = 0;
  }

  /**
   * Prints the string into the buffer. If the length of the buffer exceeds the line limit, the
   * buffer is printed on the OutputStream.
   *
   * @param s string to be printed
   */
  public synchronized void print(String s) {
    // "synchronized" protects the print method and makes it thread-save
    lengthCounter += s.length();
    line += s;

    if (lengthCounter > lineLimit) {
      printLine();
    }
  }

  private void printLine() {
    printStream.println(line);
    line = "";
    lengthCounter = 0;
  }

  public void flush() {
    printLine();
  }
}
