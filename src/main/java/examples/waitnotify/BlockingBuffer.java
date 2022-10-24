package examples.waitnotify;

/**
 * The BlockingBuffer does the thread synchronization by itself. So the code of SimpleProducer
 * and SimpleConsumer gets rid of synchronisation aspects. The two main methods put and take are
 * blocking the executing thread in case that the buffer is full / empty.
 *
 * @param <E> Class of elements to be buffered
 */
public class BlockingBuffer<E> extends Buffer<E> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BlockingBuffer.class);

  /**
   * Constructor.
   *
   * @param maxSize Maximum number of elements the buffer can hold
   */
  public BlockingBuffer(final int maxSize) {
    super(maxSize);
  }

  /**
   * Puts an element into the buffer. If the buffer is full this call blocks until there is space
   * in the buffer and the element could be inserted.
   *
   * @param element to be inserted into the buffer.
   * @throws InterruptedException if the executing thread gets interrupted while wait()
   */
  public void put(E element) throws InterruptedException {
    boolean notInserted = true;
    do {
      try {
        add(element);
        notInserted = false;
        synchronized (syncWhenEmpty) {
          syncWhenEmpty.notifyAll();
        }
      } catch (NoSpaceLeftException e) {
        synchronized (syncWhenFull) {
          syncWhenFull.wait();
        }
      }
    } while (notInserted);
  }

  /**
   * Extracts an element from the buffer and removes it in the buffer. If the buffer is empty
   * this call blocks until an element has been inserted into the buffer and the executing thread
   * succeeded in obtaining this element
   *
   * @return element from the buffer
   * @throws InterruptedException if the executing thread gets interrupted while wait()
   */
  public E take() throws InterruptedException {
    E element = null;
    boolean gotNoElement = true;
    do {
      try {
        element = remove();
        gotNoElement = false;
        synchronized (syncWhenFull) {
          syncWhenFull.notifyAll();
        }
      } catch (BufferEmptyException e) {
        synchronized (syncWhenEmpty) {
          syncWhenEmpty.wait();
        }
      }
    } while (gotNoElement);
    return element;
  }
}
