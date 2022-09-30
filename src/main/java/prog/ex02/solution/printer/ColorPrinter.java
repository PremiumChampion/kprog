package prog.ex02.solution.printer;

/**
 * Realizes a color Printer.
 */
public class ColorPrinter extends BasePrinter {

  /**
   * creates a color printer.
   *
   * @param name          the name of the printer.
   * @param duplexCapable if the printer is able to print duplex.
   */
  public ColorPrinter(String name, boolean duplexCapable) {
    super(name, duplexCapable);
  }

  @Override
  public boolean hasColor() {
    return true;
  }
}
