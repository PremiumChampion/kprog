package examples.i18n.listresourcebundles;

import java.util.ListResourceBundle;

/**
 * Simple stats bundle for Japan to demonstrate ListResourceBundle technique.
 */
public class StatsBundle_ja_JP extends ListResourceBundle {

  private final Object[][] contents = {
      {"GDP", 21300},
      {"Population", 125449703},
      {"Literacy", 0.99},
  };

  public Object[][] getContents() {
    return contents;
  }
}

