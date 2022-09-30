package prog.ex03.exercise.printer;

import prog.ex03.exercise.printer.exceptions.NoColorPrinterException;
import prog.ex03.exercise.printer.exceptions.NoDuplexPrinterException;
import prog.ex03.exercise.printer.exceptions.NotEnoughPaperException;

/**
 * Interface to a common printer.
 */
public interface Printer {
  /**
   * Prints the given document.
   *
   * @param document document to print
   * @param duplex   flag to signal if the document should be printed two-sided
   * @throws IllegalArgumentException if the document is a null reference
   * @throws NotEnoughPaperException  if there is not enough paper in the paper tray
   * @throws NoDuplexPrinterException if duplex ist required but not provided by the printer
   * @throws NoColorPrinterException  if it is a color document but the printer cannot provide color
   */
  void print(Document document, boolean duplex) throws IllegalArgumentException,
          NotEnoughPaperException, NoDuplexPrinterException, NoColorPrinterException;

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
   * @throws IllegalArgumentException if the numberOfSheets is lower zero.
   */
  void addPaper(int numberOfSheets) throws IllegalArgumentException;

  /**
   * Getter for property numberOfSheetsOfPaper.
   *
   * @return returns the number of sheets of paper in the paper tray.
   */
  int getNumberOfSheetsOfPaper();
}
