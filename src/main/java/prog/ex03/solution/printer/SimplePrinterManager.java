package prog.ex03.solution.printer;

import java.util.List;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.PrinterManager;
import prog.ex03.exercise.printer.exceptions.PrinterAlreadyRegisteredException;
import prog.ex03.exercise.printer.exceptions.PrinterNotRegisteredException;


/**
 * Implements the PrinterManager in a simple and straightforward way.
 */
public class SimplePrinterManager implements PrinterManager {

  @Override
  public Printer getPrinter(final String name)
          throws IllegalArgumentException, PrinterNotRegisteredException {
    return null;
  }

  @Override
  public List<Printer> getAllPrinters() {
    return null;
  }

  @Override
  public void addPrinter(final Printer printer)
          throws IllegalArgumentException, PrinterAlreadyRegisteredException {

  }

  @Override
  public void removePrinter(final String name)
          throws IllegalArgumentException, PrinterNotRegisteredException {

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
