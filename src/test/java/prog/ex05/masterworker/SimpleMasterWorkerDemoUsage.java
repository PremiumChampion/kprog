package prog.ex05.masterworker;

import java.util.ArrayList;
import java.util.List;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.solution.masterworker.SimpleMaster;


public class SimpleMasterWorkerDemoUsage {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleMasterWorkerDemoUsage.class);

  static final int NO_SUCCEEDING_RUNNABLES = 20;
  static final int NO_FAILING_RUNNABLES = 5;

  public static void main(String[] args) throws InterruptedException {
    Master master = new SimpleMaster(4);

    List<Task> succeedingTaskList = new ArrayList<>();
    List<Task> failingTaskList = new ArrayList<>();

    // Create succeeding tasks and add them for later inspection to a List of Tasks.
    for (int loop = 0; loop < NO_SUCCEEDING_RUNNABLES; loop++) {
      SucceedingRunnable succeedingRunnable = new SucceedingRunnable(50);
      succeedingTaskList.add(master.addTask(succeedingRunnable));
    }

    Thread.sleep(500);

    // First check of the state of the Tasks.
    System.err.println("---- The succeeding tasks ");
    for (Task task : succeedingTaskList) {
      System.err.println("Task " + task.getId() + ", TaskState =  " + task.getState());
    }


    // Create failing tasks and add them for later inspection to a List of Tasks.
    for (int loop = 0; loop < NO_FAILING_RUNNABLES; loop++) {
      FailingRunnable failingRunnable = new FailingRunnable(
                      new RuntimeException("This is a dummy exception"), 50);
      failingTaskList.add(master.addTask(failingRunnable));
    }

    // Enough time to let all tasks finish
    Thread.sleep(3000);

    // Terminate the worker threads
    master.shutdown();

    Thread.sleep(3000);


    // Another check of the task states, the successful ones
    System.err.println("---- The succeeding tasks ");
    for (Task task : succeedingTaskList) {
      System.err.println("Task " + task.getId() + ", TaskState =  " + task.getState());
    }

    // Another check of the task states, the failing ones
    System.err.println("---- The failing tasks ");
    for (Task task : failingTaskList) {
      System.err.println("Task " + task.getId() + ", TaskState =  " + task.getState());
    }



  }
}
