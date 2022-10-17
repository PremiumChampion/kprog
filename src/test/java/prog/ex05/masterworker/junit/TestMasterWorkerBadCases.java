package prog.ex05.masterworker.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.solution.masterworker.SimpleMaster;

public class TestMasterWorkerBadCases {

  private Master master;

  @Before
  public void setup() {
    master = new SimpleMaster(4);
  }

  @After
  public void teardown() {
    master.shutdown();
    master = null;
  }

  @Test @Ignore
  public void testRunnableNullReference() throws IllegalArgumentException {
    try {
      master.addTask(null);
      fail(
          "If the runnable is a Null Reference, an IllegalArgumentException must be thrown when calling addTask()");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test @Ignore
  public void testTaskIdNotKnown() throws IllegalArgumentException {
    try {
      master.getTask(99999);
      fail(
          "If the taskId does not exist an IllegalArgumentException must be thrown when calling getTask()");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      master.getTaskState(99999);
      fail(
          "If the taskId does not exist, an IllegalArgumentException must be thrown when calling getTaskState()");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test @Ignore
  public void testToLessWorkers() throws IllegalArgumentException {
    try {
      SimpleMaster m1 = new SimpleMaster(0);
      fail(
          "If the numberOfWorkers is under 1, an IllegalArgumentException must be thrown when creating a master");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}
