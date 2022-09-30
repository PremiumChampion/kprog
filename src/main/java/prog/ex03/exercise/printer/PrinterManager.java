package prog.ex03.exercise.printer;

import java.util.List;
import prog.ex03.exercise.printer.exceptions.PrinterAlreadyRegisteredException;
import prog.ex03.exercise.printer.exceptions.PrinterNotRegisteredException;

/**
 * Interface to a PrinterManager to manage a set of printers on a computer.
 */
public interface PrinterManager {
  /**
   * returns a printer with the given name.
   *
   * @param name name of the requested printer.
   * @return Printer with the given name
   * @throws IllegalArgumentException if the name is not a readable string or a null reference
   * @throws PrinterNotRegisteredException if no printer is registered under the given name
   */
  Printer getPrinter(String name) throws IllegalArgumentException, PrinterNotRegisteredException;

  /**
   * Returns all printers known by the PrinterManager.
   *
   * @return List of Printer objects. If no printer is known by the PrinterManager, it return an
   *         empty list.
   */
  List<Printer> getAllPrinters();

  /**
   * Adds a printer to the PrinterManager. The name of the printer must contain printable
   * characters and must be unique within the PrinterManager.
   *
   * @param printer printer to be added to the PrinterManager
   * @throws IllegalArgumentException if the parameter is a null reference or if the printer name
   *         does not contain printable characters
   * @throws PrinterAlreadyRegisteredException if the name of the printer is akreadyy known
   *         within the PrinterManager
   */
  void addPrinter(Printer printer) throws IllegalArgumentException,
          PrinterAlreadyRegisteredException;

  /**
   * Removes the printer with the given name from the PrinterManager.
   *
   * @param name name of the Printer to be removed.
   * @throws IllegalArgumentException if the parameter is a null reference
   * @throws PrinterNotRegisteredException if the given name does not exist within the
   *         PrinterManager
   */
  void removePrinter(String name) throws IllegalArgumentException, PrinterNotRegisteredException;

  /**
   * Returns the number of ColorPrinters within the PrinterManager.
   *
   * @return number of ColorPrinters within the PrinterManager.
   */
  int getNumberOfColorPrinters();

  /**
   * Returns the number of Black-and-White-Printers within the PrinterManager.
   *
   * @return number of BwPrinters within the PrinterManager.
   */
  int getNumberOfBwPrinters();
}
