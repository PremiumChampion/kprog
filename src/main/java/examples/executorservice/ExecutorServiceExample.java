package examples.executorservice;

import static examples.executorservice.LongRunningFunctionCalls.f;
import static examples.executorservice.LongRunningFunctionCalls.g;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example of Executor Service, from "Modern Java in Action", Chapter 15
 */
public class ExecutorServiceExample {
  public static void main(String[] args)
      throws ExecutionException, InterruptedException {
    int x = 1337;
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<Integer> y = executorService.submit(() -> f(x));
    Future<Integer> z = executorService.submit(() -> g(x));
    System.out.println(y.get() + z.get());
    executorService.shutdown();
  }
}
