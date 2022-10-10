package prog.ex03.printer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex03.exercise.printer.Document;
import prog.ex03.exercise.printer.exceptions.NoColorPrinterException;
import prog.ex03.exercise.printer.exceptions.NoDuplexPrinterException;
import prog.ex03.exercise.printer.exceptions.NotEnoughPaperException;
import prog.ex03.solution.printer.BasePrinter;
import prog.ex03.solution.printer.BwPrinter;

public class TestBasePrinter {

  public static final String SIMPLEX_PRINTER = "Simple One";
  public static final String DUPLEX_PRINTER = "Duplex One";
  public static final int NUMBER_OF_SHEETS = 123;
  private BasePrinter baseSimplexPrinter;
  private BasePrinter baseDuplexPrinter;
  private Document document42bw;
  private Document document42color;
  private Document document23bw;
  private Document document23color;

  @Before
  public void setup() {
    baseSimplexPrinter = new BwPrinter(SIMPLEX_PRINTER, false);
    baseDuplexPrinter = new BwPrinter(DUPLEX_PRINTER, true);
    document42bw = new Document(42, false);
    document42color = new Document(42, true);
    document23bw = new Document(23, false);
    document23color = new Document(23, true);
  }

  @Test @Ignore
  public void testPaperManagement() {
    assertEquals(0, baseSimplexPrinter.getNumberOfSheetsOfPaper());
    assertEquals(0, baseDuplexPrinter.getNumberOfSheetsOfPaper());

    baseSimplexPrinter.addPaper(NUMBER_OF_SHEETS);
    baseDuplexPrinter.addPaper(2 * NUMBER_OF_SHEETS);

    assertEquals(NUMBER_OF_SHEETS, baseSimplexPrinter.getNumberOfSheetsOfPaper());
    assertEquals(2*NUMBER_OF_SHEETS, baseDuplexPrinter.getNumberOfSheetsOfPaper());

    baseSimplexPrinter.addPaper(0);
    baseDuplexPrinter.addPaper(0);
  }

  @Test @Ignore
  public void addNegativeNumberOfSheetsOfPaper() {
    try {
      baseSimplexPrinter.addPaper(-100);
      fail("Adding negative number of sheets of paper is not allowed.");
    } catch (IllegalArgumentException e) {
      // success
    }

    try {
      baseDuplexPrinter.addPaper(-100);
      fail("Adding negative number of sheets of paper is not allowed.");
    } catch (IllegalArgumentException e) {
      // success
    }
  }

  @Test @Ignore
  public void testPrintingSimplex()
          throws NoDuplexPrinterException, NotEnoughPaperException, NoColorPrinterException {
    baseSimplexPrinter.addPaper(100);
    baseSimplexPrinter.print(document23bw, false);
    assertEquals(77, baseSimplexPrinter.getNumberOfSheetsOfPaper());

    try {
      baseSimplexPrinter.print(document23bw, true);
      fail("It should not be possible to print in duplex mode on a non-duplex printer.");
    } catch (NoDuplexPrinterException e) {
      // success
    }
  }

  @Test @Ignore
  public void testPrintingDuplex()
          throws NoDuplexPrinterException, NotEnoughPaperException, NoColorPrinterException {
    baseDuplexPrinter.addPaper(12);
      baseDuplexPrinter.print(document23bw, true);
    assertEquals(0, baseDuplexPrinter.getNumberOfSheetsOfPaper());
  }

  @Test @Ignore
  public void testPrintNullDocument() {
    baseSimplexPrinter.addPaper(100);
    try {
      baseSimplexPrinter.print(null, false);
      fail("It should not be possible to print a null document.An exception is expected.");
    }  catch (NotEnoughPaperException | NoColorPrinterException | NoDuplexPrinterException e) {
      fail("Wrong Exception. IllegalArgumentException expected.");
    } catch (IllegalArgumentException e) {
      // success
    }
  }

  @Test @Ignore
  public void testColorDocumentOnBwPrinter() {
    baseSimplexPrinter.addPaper(100);
    try {
      baseSimplexPrinter.print(document23color, false);
      fail("It should not be possible to print a color document on a bw printer. An exception is " +
              "expected.");
    } catch (NoDuplexPrinterException | NotEnoughPaperException | IllegalArgumentException e) {
      fail("Wrong Exception. IllegalArgumentException expected.");
    } catch (NoColorPrinterException e) {
      // success
    }
  }

}
