package prog.ex05.masterworker;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A ThreadNameCollector collects the names of executing threads and counts which thread name
 * appears how often.
 */
public class ThreadNameCollector {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ThreadNameCollector.class);

  private Map<String, AtomicInteger> threadNameMap;

  public ThreadNameCollector() {
    threadNameMap = new HashMap<>();
  }

  public synchronized void addThreadName(String name) {
    if (!threadNameMap.containsKey(name)) {
      threadNameMap.put(name, new AtomicInteger(0));
    }
    threadNameMap.get(name).incrementAndGet();
  }

  public synchronized  int getNumberOfThreadNames() {
    return threadNameMap.keySet().size();
  }

  public void printThreadNamesAndNumbers(PrintStream stream) {
    Set<String> keySet = threadNameMap.keySet();
    for (String name : keySet) {
      stream.println(name + " : " + threadNameMap.get(name));
    }
  }
}
