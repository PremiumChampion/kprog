package prog.ex03.solution.printer;

/**
 * Realizes a Black-And-White Printer.
 */
public class BwPrinter extends BasePrinter {

  /**
   * Creates a black and whit printer.
   *
   * @param name          the name of the printer.
   * @param duplexCapable if the printer is able to print duplex.
   */
  public BwPrinter(final String name, final boolean duplexCapable) {
    super(name, duplexCapable);
  }

  @Override
  public boolean hasColor() {
    return false;
  }
}
