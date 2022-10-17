package prog.ex05.masterworker.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.solution.masterworker.SimpleMaster;

public class TestWorkerThreads {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestWorkerThreads.class);

  private List<Thread> getAllThreadsOfGroup() {
    int activeThreads = Thread.currentThread().getThreadGroup().activeCount();
    Thread[] threadField = new Thread[2 * activeThreads];
    int storedThreads = Thread.currentThread().getThreadGroup().enumerate(threadField);
    if (storedThreads == threadField.length) {
      logger.warn("Thread field completely filled. Maybe some threads are missing.");
    }
    List<Thread> threadList = new ArrayList<>();
    threadList.addAll(Arrays.asList(threadField));
    return threadList;
  }

  private Map<String, Thread> getThreadMap(List<Thread> threadList) {
    Map<String, Thread> threadMap = new HashMap<>();
    for (Thread thread : threadList) {
      if (thread == null) {
        continue;
      }
      threadMap.put(thread.getName(), thread);
    }
    return threadMap;
  }

  @Test @Ignore
  public void checkWorkerNames() throws InterruptedException {
    Map<String, Thread> threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    Master master = new SimpleMaster(4);
    Thread.sleep(200);
    List<String> names = master.getWorkerNames();
    assertNotNull("getWorkerNames should not result in null reference.", names);
    assertEquals("There should be 4 worker thread names.", 4, names.size());
    assertTrue("Not all Thread names are unique", allThreadNamesAreUnique(names));
    threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    assertTrue("Not all Thread names are valid.", allThreadNamesAreValid(threadMap, names));
    master.shutdown();
    Thread.sleep(800);
    threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    assertTrue("No Thread name should be valid after shutdown of the Master.",
            noThreadNameIsValid(threadMap, names));
  }

  private boolean allThreadNamesAreUnique(final List<String> names) {
    Set<String> nameSet = new HashSet<>();
    for (String name : names) {
      nameSet.add(name);
    }

    if (names.size() == nameSet.size()) {
      return true;
    }
    return false;
  }


  private boolean allThreadNamesAreValid(final Map<String, Thread> threadMap,
                                         final List<String> names) {
    for (String name : names) {
      if (!threadMap.containsKey(name)) {
        return false;
      }
    }
    return true;
  }

  private boolean noThreadNameIsValid(final Map<String, Thread> threadMap,
                                         final List<String> names) {
    for (String name : names) {
      if (threadMap.containsKey(name)) {
        return false;
      }
    }
    return true;
  }
}

