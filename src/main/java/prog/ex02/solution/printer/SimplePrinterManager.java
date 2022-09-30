package prog.ex02.solution.printer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prog.ex02.exercise.printer.Printer;
import prog.ex02.exercise.printer.PrinterManager;


/**
 * Realizes a simple PrintManager.
 */
public class SimplePrinterManager implements PrinterManager {

  // stores available printer.
  private final Map<String, Printer> availablePrinter = new HashMap<>();


  @Override
  public Printer getPrinter(final String name) {
    return availablePrinter.get(name);
  }

  @Override
  public List<Printer> getAllPrinters() {
    return List.copyOf(this.availablePrinter.values());
  }

  @Override
  public boolean addPrinter(final Printer printer) {
    if (printer == null) {
      return false;
    }

    if (printer.getName() != null && printer.getName().trim().length() == 0) {
      return false;
    }

    if (this.getPrinter(printer.getName()) != null) {
      return false;
    }

    this.availablePrinter.put(printer.getName(), printer);

    return true;
  }

  @Override
  public boolean removePrinter(final String name) {

    if (name == null) {
      return false;
    }

    if (this.getPrinter(name) == null) {
      return false;
    }

    this.availablePrinter.remove(name);

    return true;
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

  @Override
  public int getNumberOfColorPrinters() {
    return this.getPrinterCountByColor(true);
  }

  @Override
  public int getNumberOfBwPrinters() {
    return this.getPrinterCountByColor(false);
  }
}
