package examples.waitnotify;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Simple generic Buffer class without thread synchronisation mechanisms.
 *
 * @param <E> class of elements the buffer shall accept
 */
public class Buffer<E> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Buffer.class);

  private final int maxSize;
  private int currentSize;
  // Object for wait/notify when buffer is full
  public final Object syncWhenFull;
  // Object for wait/notify when buffer is empty
  public final Object syncWhenEmpty;

  private ConcurrentLinkedQueue<E> queue;

  /**
   * Constructor.
   *
   * @param maxSize Maximum number of elements the buffer can hold.
   */
  public Buffer(final int maxSize) {
    syncWhenFull = new Object();
    syncWhenEmpty = new Object();
    this.maxSize = maxSize;
    this.currentSize = 0;
    queue = new ConcurrentLinkedQueue<>();
  }

  /**
   * Adds an element to the buffer.
   *
   * @param element element to be added
   * @throws NoSpaceLeftException if the buffer is full
   */
  public void add(E element) throws NoSpaceLeftException {
    if (currentSize == maxSize) {
      throw new NoSpaceLeftException("No space left in buffer.");
    }
    synchronized (queue) {
      queue.add(element);
      currentSize++;
    }
  }

  /**
   * Extracts an element from the buffer and removes it from the buffer.
   *
   * @return extracted element
   * @throws BufferEmptyException if the buffer is empty
   */
  public E remove() throws BufferEmptyException {
    synchronized (queue) {
      if (queue.isEmpty()) {
        throw new BufferEmptyException("Buffer is empty.");
      }
      currentSize--;
      return queue.poll();
    }
  }

  public int getMaxSize() {
    return maxSize;
  }

  public int getCurrentSize() {
    return currentSize;
  }

  public boolean isFull() {
    return currentSize == maxSize;
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
