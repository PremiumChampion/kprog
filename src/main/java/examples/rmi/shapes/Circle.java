package examples.rmi.shapes;

import java.io.Serializable;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple shapes.shapes.Circle class for demonstration purpose.
 */
public class Circle extends GraphicalObject implements Serializable {
  private static final Logger logger = LoggerFactory.getLogger(Circle.class);
  private final int radius;

  public Circle(int radius) {
    this.radius = radius;
    logger.info("Create new circle with radius " + radius);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Circle.class.getSimpleName() + "[", "]")
        .add("radius=" + radius)
        .toString();
  }
}
