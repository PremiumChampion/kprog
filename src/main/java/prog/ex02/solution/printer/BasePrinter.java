package prog.ex02.solution.printer;

import prog.ex02.exercise.printer.Document;
import prog.ex02.exercise.printer.Printer;

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
  public boolean print(final Document document, final boolean duplex) {

    if (document == null) {
      return false;
    }

    if (document.isColor() && !this.hasColor()) {
      return false;
    }

    if (duplex && !this.hasDuplex()) {
      return false;
    }

    int sheetCountForPrintJob = BasePrinter.getSheetCountForPrintJob(document, duplex);

    if (sheetCountForPrintJob > this.getNumberOfSheetsOfPaper()) {
      return false;
    }

    this.setSheetCount(this.getNumberOfSheetsOfPaper() - sheetCountForPrintJob);

    return true;
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
  public boolean addPaper(final int numberOfSheets) {

    if (numberOfSheets < 0) {
      return false;
    }

    this.setSheetCount(this.getNumberOfSheetsOfPaper() + numberOfSheets);

    return true;
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
