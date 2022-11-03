package examples.javafx.modal;

public class ExceptionThrower {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ExceptionThrower.class);

  private Class exceptionType;

  public ExceptionThrower(final Class exceptionType) {
    this.exceptionType = exceptionType;
  }

  public void simulateAnException() throws Exception {
    Exception exception = (Exception) exceptionType
        .getConstructor(String.class)
        .newInstance("OMG. This is exception karaoke. I think I am a "
            + exceptionType.getSimpleName() + ".");
    throw exception;
  }
}
