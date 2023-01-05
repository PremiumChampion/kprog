package examples.i18n.listresourcebundles;

import java.util.ListResourceBundle;

/**
 * Simple stats bundle for France to demonstrate ListResourceBundle technique.
 */
public class StatsBundle_fr_FR extends ListResourceBundle {

  private final Object[][] contents = {
      {"GDP", 20200},
      {"Population", 58317450},
      {"Literacy", 0.99},
  };

  public Object[][] getContents() {
    return contents;
  }
}

