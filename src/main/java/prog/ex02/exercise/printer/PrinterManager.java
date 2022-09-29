package prog.ex02.exercise.printer;

import java.util.List;

/**
 * Interface to a PrinterManager to manage a set of printers on a computer.
 */
public interface PrinterManager {
  /**
   * returns a printer with the given name.
   *
   * @param name name of the requested printer.
   * @return Printer with the given name, otherwise null.
   */
  Printer getPrinter(String name);

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
   * @return false if
   *<ul>
   *   <li>the parameter is a null reference</li>
   *   <li>the name of the printer is already known within the PrinterManager</li>
   *   <li>the name of the printer does not contain printable characters</li>
   *</ul> otherwise true.
   */
  boolean addPrinter(Printer printer);

  /**
   * Removes the printer with the given name from the PrinterManager.
   *
   * @param name name of the Printer to be removed.
   * @return false if
   *<ul>
   *   <li>the parameter is a null reference</li>
   *   <li>the printer with the given name does not exist within the PrinterManager</li>
   *</ul> otherwise true.
   */
  boolean removePrinter(String name);

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
