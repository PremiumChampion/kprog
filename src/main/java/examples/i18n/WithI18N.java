package examples.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Simple example to demonstrate the PropertyResourceBundle technique.
 */
public class WithI18N {

  /**
   * Demo of an example with I18N.
   *
   * @param args not relevant
   */
  public static void main(String[] args) {
    ResourceBundle messages = ResourceBundle.getBundle("bundles/DemoI18N",
        Locale.GERMANY);

    System.out.println(messages.getString("greetings"));
    System.out.println(messages.getString("inquiry"));
    System.out.println(messages.getString("farewell"));
  }
}

