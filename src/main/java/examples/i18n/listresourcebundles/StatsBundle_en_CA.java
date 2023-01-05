package examples.i18n.listresourcebundles;

import java.util.ListResourceBundle;

/**
 * Simple stats bundle for Canada to demonstrate ListResourceBundle technique.
 */
public class StatsBundle_en_CA extends ListResourceBundle {

  private final Object[][] contents = {
      {"GDP", 24400},
      {"Population", 28802671},
      {"Literacy", 0.97},
  };

  public Object[][] getContents() {
    return contents;
  }
}

