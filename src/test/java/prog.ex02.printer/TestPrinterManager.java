package prog.ex02.printer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex02.exercise.printer.Printer;
import prog.ex02.exercise.printer.PrinterManager;
import prog.ex02.solution.printer.BwPrinter;
import prog.ex02.solution.printer.ColorPrinter;
import prog.ex02.solution.printer.SimplePrinterManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPrinterManager {


  public static final String SIMPLEX_PRINTER_1 = "simplex 1";
  public static final String SIMPLEX_PRINTER_2 = "simplex 2";
  public static final String DUPLEX_PRINTER_1 = "simplex 2";
  public static final String BW_SIMPLEX_1 = "bw-simplex-1";
  public static final String COLOR_DUPLEX_1 = "color-duplex-1";
  private PrinterManager printerManager;
  private BwPrinter bwSimplexPrinter1;
  private ColorPrinter colorDuplexPrinter1;

  @Before
  public void setup() {
    printerManager = new SimplePrinterManager();
    bwSimplexPrinter1 = new BwPrinter(BW_SIMPLEX_1, false);
    colorDuplexPrinter1 = new ColorPrinter(COLOR_DUPLEX_1, true);
  }

  @Test  @Ignore
  public void testPrintManagerIsEmpty() {
    assertEquals(0, printerManager.getNumberOfBwPrinters());
    assertEquals(0, printerManager.getNumberOfColorPrinters());
    assertNotNull(printerManager.getAllPrinters());
    assertEquals(0, printerManager.getAllPrinters().size());
  }

  @Test  @Ignore
  public void testAddGoodBwPrinter(){
    printerManager.addPrinter(bwSimplexPrinter1);
    assertEquals(0, printerManager.getNumberOfColorPrinters());
    assertEquals(1, printerManager.getNumberOfBwPrinters());
    List<Printer> printerList = printerManager.getAllPrinters();
    assertNotNull(printerList);
    assertEquals(1, printerList.size());
    Printer tmpPrinter = printerList.get(0);
    assertEquals(BW_SIMPLEX_1, tmpPrinter.getName());
  }
}
