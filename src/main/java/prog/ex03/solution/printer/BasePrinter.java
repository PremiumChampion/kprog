package prog.ex03.solution.printer;

import prog.ex03.exercise.printer.Document;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.exceptions.NoColorPrinterException;
import prog.ex03.exercise.printer.exceptions.NoDuplexPrinterException;
import prog.ex03.exercise.printer.exceptions.NotEnoughPaperException;

/**
 * Realizes a basic Printer implementation.
 */
public abstract class BasePrinter implements Printer {

  // the name of the printer
  String name;
  // sheets in the printer
  int sheetCount;
  // if the printer is duplex capable
  boolean isDuplexCapable;

  /**
   * creates a base printer and fills necessary fields.
   *
   * @param name            name of the printer.
   * @param isDuplexCapable if the printer is able to print duplex.
   */
  public BasePrinter(final String name, final boolean isDuplexCapable) {
    this.name = name;
    this.sheetCount = 0;
    this.isDuplexCapable = isDuplexCapable;
  }

  @Override
  public void print(final Document document, final boolean duplex)
      throws IllegalArgumentException, NoColorPrinterException, NoDuplexPrinterException,
      NotEnoughPaperException {

    if (document == null) {
      throw new IllegalArgumentException();
    }

    if (document.isColor() && !this.hasColor()) {
      throw new NoColorPrinterException("this printer does not support printing color");
    }

    if (duplex && !this.hasDuplex()) {
      throw new NoDuplexPrinterException("this printer does not support duplex");
    }

    int sheetCountForPrintJob = BasePrinter.getSheetCountForPrintJob(document, duplex);

    if (sheetCountForPrintJob > this.getNumberOfSheetsOfPaper()) {
      throw new NotEnoughPaperException("there is not enough paper in this printer",
          sheetCountForPrintJob - this.getNumberOfSheetsOfPaper());
    }

    this.setSheetCount(this.getNumberOfSheetsOfPaper() - sheetCountForPrintJob);
    document.hasBeenPrinted();
  }

  /**
   * calculates the count of pages necessary for a print job.
   *
   * @param document the document to print.
   * @param duplex   if the doc should be printed duplex.
   * @return the count of sheets necessary for the job.
   */
  private static int getSheetCountForPrintJob(Document document, boolean duplex) {
    int absolutePageCountPerCopy = document.getPages();

    if (duplex) {
      absolutePageCountPerCopy = (int) ((absolutePageCountPerCopy + 1) / 2);
    }

    return absolutePageCountPerCopy;
  }

  @Override
  public boolean hasDuplex() {
    return this.isDuplexCapable;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addPaper(final int numberOfSheets) throws IllegalArgumentException {

    if (numberOfSheets < 0) {
      throw new IllegalArgumentException();
    }

    this.setSheetCount(this.getNumberOfSheetsOfPaper() + numberOfSheets);
  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return this.sheetCount;
  }

  /**
   * sets the sheet-count.
   *
   * @param numberOfSheetsOfPaper the new sheet-count of the printer.
   */
  protected void setSheetCount(int numberOfSheetsOfPaper) {
    this.sheetCount = numberOfSheetsOfPaper;
  }
}
