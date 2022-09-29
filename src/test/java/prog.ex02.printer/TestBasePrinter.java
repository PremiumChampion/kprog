package prog.ex02.printer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex02.exercise.printer.Document;
import prog.ex02.solution.printer.BasePrinter;
import prog.ex02.solution.printer.BwPrinter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    assertTrue(baseSimplexPrinter.addPaper(NUMBER_OF_SHEETS));
    assertTrue(baseDuplexPrinter.addPaper(2 * NUMBER_OF_SHEETS));

    assertEquals(NUMBER_OF_SHEETS, baseSimplexPrinter.getNumberOfSheetsOfPaper());
    assertEquals(2*NUMBER_OF_SHEETS, baseDuplexPrinter.getNumberOfSheetsOfPaper());

    assertTrue(baseSimplexPrinter.addPaper(0));
    assertTrue(baseDuplexPrinter.addPaper(0));
  }

  @Test @Ignore
  public void addNegativeNumberOfSheetsOfPaper() {
    assertFalse(baseSimplexPrinter.addPaper(-100));
    assertFalse(baseDuplexPrinter.addPaper(-100));
  }

  @Test @Ignore
  public void testPrintingSimplex() {
    assertTrue(baseSimplexPrinter.addPaper(100));
    assertTrue(baseSimplexPrinter.print(document23bw, false));
    assertFalse(baseSimplexPrinter.print(document23bw, true));
    assertEquals(77, baseSimplexPrinter.getNumberOfSheetsOfPaper());
  }

  @Test @Ignore
  public void testPrintingDuplex() {
    assertTrue(baseDuplexPrinter.addPaper(12));
    assertTrue(baseDuplexPrinter.print(document23bw, true));
    assertEquals(0, baseDuplexPrinter.getNumberOfSheetsOfPaper());
  }
}
