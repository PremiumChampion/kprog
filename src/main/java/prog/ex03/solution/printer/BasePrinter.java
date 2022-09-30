package prog.ex03.solution.printer;

import prog.ex03.exercise.printer.Document;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.exceptions.NoColorPrinterException;
import prog.ex03.exercise.printer.exceptions.NoDuplexPrinterException;
import prog.ex03.exercise.printer.exceptions.NotEnoughPaperException;

/**
 * Abstract class for BwPrinter and ColorPrinter.
 */
public abstract class BasePrinter implements Printer {

  @Override
  public void print(final Document document, final boolean duplex) throws
          IllegalArgumentException, NotEnoughPaperException,
          NoDuplexPrinterException, NoColorPrinterException {

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
  public void addPaper(final int numberOfSheets) throws IllegalArgumentException {

  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return 0;
  }
}
