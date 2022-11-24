package examples.executorservice;

import static examples.executorservice.LongRunningFunctionCalls.bar;
import static examples.executorservice.LongRunningFunctionCalls.foo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example of Executor Service, from "Modern Java in Action", Chapter 15.
 */
public class ExecutorServiceExample {

  /**
   * Main.
   *
   * @param args no args expected.
   * @throws Exception If anything in this demo fails.
   */
  public static void main(String[] args)
      throws Exception {
    int x = 1337;
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<Integer> y = executorService.submit(() -> foo(x));
    Future<Integer> z = executorService.submit(() -> bar(x));
    System.out.println(y.get() + z.get());
    executorService.shutdown();
  }
}
