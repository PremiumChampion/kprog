package prog.ex02.solution.printer;

import java.util.List;
import prog.ex02.exercise.printer.Printer;
import prog.ex02.exercise.printer.PrinterManager;



/**
 * Realizes a simple PrintManager.
 */
public class SimplePrinterManager implements PrinterManager {

  @Override
  public Printer getPrinter(final String name) {
    return null;
  }

  @Override
  public List<Printer> getAllPrinters() {
    return null;
  }

  @Override
  public boolean addPrinter(final Printer printer) {
    return false;
  }

  @Override
  public boolean removePrinter(final String name) {
    return false;
  }

  @Override
  public int getNumberOfColorPrinters() {
    return 0;
  }

  @Override
  public int getNumberOfBwPrinters() {
    return 0;
  }
}
