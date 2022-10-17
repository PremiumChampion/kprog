package examples.threads.simplequeue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class NonConcurrentQueueDemo {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(NonConcurrentQueueDemo.class);
  private static final int MAX_THEADS = 100;

  public static void main(String[] args) {
    Queue<Food> queue = new LinkedList<>();

    List<MunchingThread> threadList = new ArrayList<>();
    
    // Create the threads
    for (int loop = 0; loop < MAX_THEADS; loop++) {
      threadList.add(new MunchingThread(queue));
    }

    boolean producingFood = true;
    int foodCounter = 1;

    System.out.println("Give the number of food objects to create:");
    while (producingFood) {
      int numberOfFoods = readFromKeyboard();
      if (numberOfFoods == 0) {
        producingFood = false;
      } else {
        System.out.println("creating " + numberOfFoods + " food objects.");
        for (int loop = 0; loop < numberOfFoods; loop++) {
          queue.add(new Food("Food-Nr-" + foodCounter++));
        }
      }
    }
  }

  private static int readFromKeyboard() {
    Scanner in = new Scanner(System.in);
    return in.nextInt();
  }
}
