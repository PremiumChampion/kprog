package prog.ex05.solution.masterworker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.exercise.masterworker.Worker;

/**
 * Simple and straight-forward implementation of the Master interface.
 */
public class SimpleMaster implements Master {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SimpleMaster.class);

  private int numberOfWorkers;
  private List<Worker> workers;
  private Map<Integer, Task> allTasks;
  private ConcurrentLinkedQueue<Task> queuedTasks;

  /**
   * creates a master with a certain ammount of workers.
   *
   * @param numberOfWorkers the nummer of workers managed by this master.
   */
  public SimpleMaster(int numberOfWorkers) {
    if (numberOfWorkers < 1) {
      throw new IllegalArgumentException("can not add less than one worker.");
    }
    this.numberOfWorkers = numberOfWorkers;
    this.workers = new ArrayList<>();
    this.allTasks = new HashMap<>();
    this.queuedTasks = new ConcurrentLinkedQueue<>();
    this.generateWorker();
  }

  /**
   * generates the worker and starts them.
   */
  private void generateWorker() {
    for (int i = 0; i < this.numberOfWorkers; i++) {
      SimpleWorker w = new SimpleWorker("Worker-" + i);
      w.setQueue(this.queuedTasks);
      w.start();
      this.workers.add(w);
    }
  }

  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {
    Task newTask = new Task(runnable);
    this.allTasks.put(newTask.getId(), newTask);
    this.queuedTasks.add(newTask);
    return newTask;
  }

  @Override
  public TaskState getTaskState(final int taskId) throws IllegalArgumentException {
    if (taskId < 0) {
      throw new IllegalArgumentException("taskId must not be negative.");
    }
    if (!this.allTasks.containsKey(taskId)) {
      throw new IllegalArgumentException("taskId not found.");
    }

    return this.allTasks.get(taskId).getState();
  }

  @Override
  public Task getTask(final int taskId) throws IllegalArgumentException {
    if (taskId < 0) {
      throw new IllegalArgumentException("taskId must not be negative.");
    }
    if (!this.allTasks.containsKey(taskId)) {
      throw new IllegalArgumentException("taskId not found.");
    }
    return this.allTasks.get(taskId);
  }

  @Override
  public int getNumberOfWorkers() {
    return numberOfWorkers;
  }

  @Override
  public List<String> getWorkerNames() {
    return this.workers.stream().map(Worker::getName).collect(Collectors.toList());
  }

  @Override
  public int getNumberOfQueuedTasks() {
    return this.queuedTasks.size();
  }

  @Override
  public void shutdown() {
    this.workers.forEach(Worker::terminate);

    for (Worker worker : this.workers) {
      if (worker instanceof Thread) {
        System.out.println("joining thread");
        Thread thread = (Thread) worker;
        try {
          thread.join();
        } catch (InterruptedException e) {
          SimpleMaster.logger.error("joining threads got interrupted", e);
        }
      }
    }
  }
}
