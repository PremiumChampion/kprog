package prog.ex02.exercise.printer;

/**
 * Interface to a common printer.
 */
public interface Printer {
  /**
   * Prints the given document.
   *
   * @param document document to print
   * @param duplex   flag to signal if the document should be printed two-sided
   * @return false if
   *<ul>
   *   <li>there is not enough paper in the paper tray</li>
   *   <li>if duplex is required but not provided by the printer</li>
   *   <li>if it is a color document but the printer cannot provide color</li>
   *</ul> otherwise true
   */
  boolean print(Document document, boolean duplex);

  /**
   * Getter for property color.
   *
   * @return true if the printer can print in color, otherwise false.
   */
  boolean hasColor();

  /**
   * Getter for property duplex.
   *
   * @return true if the printer is able to print two-sided, otherwise false.
   */
  boolean hasDuplex();

  /**
   * Getter for property name.
   *
   * @return name of the printer.
   */
  String getName();

  /**
   * Adds paper to the paper tray.
   *
   * @param numberOfSheets number of sheets of paper to be added. This number must be
   *                       greater-equals zero
   * @return true if the number is greater-equals zero, otherwise false
   */
  boolean addPaper(int numberOfSheets);

  /**
   * Getter for property numberOfSheetsOfPaper.
   *
   * @return returns the number of sheets of paper in the paper tray.
   */
  int getNumberOfSheetsOfPaper();
}
