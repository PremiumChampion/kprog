package prog.ex03.solution.printer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.PrinterManager;
import prog.ex03.exercise.printer.exceptions.PrinterAlreadyRegisteredException;
import prog.ex03.exercise.printer.exceptions.PrinterNotRegisteredException;


/**
 * Realizes a simple PrintManager.
 */
public class SimplePrinterManager implements PrinterManager {

  // stores available printer.
  private final Map<String, Printer> availablePrinter = new HashMap<>();


  @Override
  public Printer getPrinter(final String name)
      throws IllegalArgumentException, PrinterNotRegisteredException {
    if (!this.validPrinterName(name)) {
      throw new IllegalArgumentException();
    }

    if (!hasPrinterAlreadyBeenRegistered(name)) {
      throw new PrinterNotRegisteredException(
          "the printer with the name " + name + " is not registered.");
    }

    return availablePrinter.get(name);
  }

  @Override
  public List<Printer> getAllPrinters() {
    return List.copyOf(this.availablePrinter.values());
  }

  @Override
  public void addPrinter(final Printer printer)
      throws IllegalArgumentException, PrinterAlreadyRegisteredException {
    if (printer == null || !this.validPrinterName(printer.getName())) {
      throw new IllegalArgumentException();
    }

    if (hasPrinterAlreadyBeenRegistered(printer.getName())) {
      throw new PrinterAlreadyRegisteredException("this printer has already been registered");
    }

    this.availablePrinter.put(printer.getName(), printer);
  }

  /**
   * check if a printer with a given name has already been registered.
   *
   * @param printerName the name to check.
   * @return if the printer has been registered.
   */
  private boolean hasPrinterAlreadyBeenRegistered(String printerName) {
    return this.availablePrinter.containsKey(printerName);
  }

  @Override
  public void removePrinter(final String name)
      throws IllegalArgumentException, PrinterNotRegisteredException {

    if (!this.validPrinterName(name)) {
      throw new IllegalArgumentException();
    }

    if (!this.hasPrinterAlreadyBeenRegistered(name)) {
      throw new PrinterNotRegisteredException(
          "the printer with the name \"" + name + "\" is not registered and can not be removed.");
    }

    this.availablePrinter.remove(name);
  }

  /**
   * counts printers by their color attribute.
   *
   * @param colorCapable indicates if the printer is color capable.
   * @return the count of printers.
   */
  private int getPrinterCountByColor(boolean colorCapable) {
    return (int) this.getAllPrinters().stream()
        .filter(printer -> printer.hasColor() == colorCapable).count();
  }

  /**
   * checks if a given name is a vlid printername.
   *
   * @param name the name to check
   * @return if the name is valid
   */
  private boolean validPrinterName(String name) {
    if (name == null) {
      return false;
    }
    if (name.trim().length() == 0) {
      return false;
    }
    return true;
  }

  @Override
  public int getNumberOfColorPrinters() {
    return this.getPrinterCountByColor(true);
  }

  @Override
  public int getNumberOfBwPrinters() {
    return this.getPrinterCountByColor(false);
  }
}
