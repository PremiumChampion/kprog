package prog.ex05.masterworker.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.masterworker.FailingRunnable;
import prog.ex05.masterworker.GrabbingThreadNameRunnable;
import prog.ex05.masterworker.PrimeFactorResult;
import prog.ex05.masterworker.PrimeFactorRunnable;
import prog.ex05.masterworker.SimpleStopWatch;
import prog.ex05.masterworker.StopWatch;
import prog.ex05.masterworker.SucceedingRunnable;
import prog.ex05.masterworker.ThreadNameCollector;
import prog.ex05.solution.masterworker.SimpleMaster;

public class TestMasterWorkerGoodCases {
  private static Logger logger = LoggerFactory.getLogger(TestMasterWorkerGoodCases.class);

  private Master master;
  private FailingRunnable failingRunnable;
  private SucceedingRunnable succeedingRunnable;

  @Before
  public void setup() {
    master = new SimpleMaster(4);
    failingRunnable = new FailingRunnable(new NullPointerException("This is a fake exception"),
            100);
    succeedingRunnable = new SucceedingRunnable(100);
  }

  @After
  public void teardown() {
    master.shutdown();
    master = null;
  }

  @Test
  @Ignore
  public void runOneFailingRunnable() throws InterruptedException {
    Task task = master.addTask(failingRunnable);
    assertNotNull("Master should have a task added.", task);
    assertNotNull("State of Task should not be null.", task.getState());
    Thread.sleep(1000);
    assertEquals("After a failingRunnable its status should be " + TaskState.CRASHED,
            TaskState.CRASHED, task.getState());
    assertNotNull("The exception as reason of failure should be in the task object",
            task.getException());
    Exception e = task.getException();
    assertTrue("Exception should be of type NullPointerException.", e.getClass().getName().contains(
            "NullPointerException"));
  }

  @Test
  @Ignore
  public void runOneSucceedingRunnable() throws InterruptedException {
    Task task = master.addTask(succeedingRunnable);
    assertNotNull("Master should have a task added.", task);
    assertNotNull("State of Task should not be null.", task.getState());
    Thread.sleep(1000);
    assertEquals("After a failingRunnable its status should be " + TaskState.SUCCEEDED,
            TaskState.SUCCEEDED, task.getState());
  }

  @Test
  @Ignore
  public void testGetTask() throws InterruptedException {
    int taskId = master.addTask(succeedingRunnable).getId();
    Thread.sleep(1000);
    assertNotNull("The task sould not be a null reference", master.getTask(taskId));
  }

  @Test
  @Ignore
  public void runLotsOfRunnables() throws InterruptedException {
    ConcurrentLinkedQueue<PrimeFactorResult> resultQueue = new ConcurrentLinkedQueue<>();
    StopWatch stopWatch = new SimpleStopWatch();
    stopWatch.startNow();
    List<Integer> taskIdList = new ArrayList<>();
    for (int loop = 0; loop < 300; loop++) {
      logger.info("Starting task for loop value {}", loop);
      taskIdList.add(master.addTask(new PrimeFactorRunnable(loop, resultQueue)).getId());
    }

    Thread.sleep(1000);

    AtomicInteger results = new AtomicInteger();
    resultQueue.stream().forEach((result) -> {
      results.getAndIncrement();
      System.out.println(result);
    });

    int numberResults = results.get();
    assertEquals(300, numberResults);
    Duration duration = stopWatch.stopNow();
    System.out.println("Calculated in millisecs: " + duration.toMillis());
    assertTrue("Took to long.", duration.toMillis() < 2000);
  }

  @Test
  @Ignore
  public void checkNumberOfWorkerThreadNames() throws InterruptedException {
    int numberOfTasks = 20;
    ThreadNameCollector collector = new ThreadNameCollector();
    assertEquals("Before running the tasks, no thread name should be in the ThreadNameCollector",
            0, collector.getNumberOfThreadNames());

    for (int loop = 0; loop < numberOfTasks; loop++) {
      master.addTask(new GrabbingThreadNameRunnable(collector, 50));
    }
    Thread.sleep(1000);

    collector.printThreadNamesAndNumbers(System.err);

    assertEquals("After running the tasks, four thread names should be in the ThreadNameCollector",
            4, collector.getNumberOfThreadNames());
  }
}
