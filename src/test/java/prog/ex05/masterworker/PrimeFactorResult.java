package prog.ex05.masterworker;

import java.util.List;

public class PrimeFactorResult {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PrimeFactorResult.class);

  private int number;
  private List<Integer> factorList;

  public PrimeFactorResult(final int number, final List<Integer> factorList) {
    this.number = number;
    this.factorList = factorList;
  }

  public int getNumber() {
    return number;
  }

  public List<Integer> getFactorList() {
    return factorList;
  }

  @Override
  public String toString() {
    return "PrimeFactorResult{" +
            "number=" + number +
            ", factorList=" + factorList +
            '}';
  }
}
