package prog.ex02.solution.printer;

import prog.ex02.exercise.printer.Document;
import prog.ex02.exercise.printer.Printer;

/**
 * Realizes a basic Printer implementation.
 */
public abstract class BasePrinter  implements Printer {

  @Override
  public boolean print(final Document document, final boolean duplex) {
    return false;
  }

  @Override
  public boolean hasDuplex() {
    return false;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean addPaper(final int numberOfSheets) {
    return false;
  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return 0;
  }
}
