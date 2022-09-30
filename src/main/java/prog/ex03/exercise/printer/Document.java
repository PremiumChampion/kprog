package prog.ex03.exercise.printer;

/**
 * Class representing a document. The content is not represented within this class.
 */
public class Document {

  // Number of pages the document consists of.
  private int pages;
  // Flag signaling if the document uses color.
  private boolean color;
  // Number of copies already printed from this document.
  private int numberOfCopies;

  public Document(final int pages, final boolean color) {
    this.pages = pages;
    this.color = color;
  }

  /**
   * Getter for property pages.
   *
   * @return number of pages the document consists of
   */
  public int getPages() {
    return pages;
  }

  /**
   * Getter for property color.
   *
   * @return true if the document contains color, false otherwise
   */
  public boolean isColor() {
    return color;
  }

  /**
   * Getter for property numberOfCopies.
   *
   * @return number of copies already successfully printed
   */
  public int getNumberOfCopies() {
    return numberOfCopies;
  }

  /**
   * This method gets called by a printer to increase the counter of copies. Should only be
   * called if the printer can successfully print the document.
   */
  public void hasBeenPrinted() {
    numberOfCopies++;
  }
}
