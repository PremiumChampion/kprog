package livesession.snake;

import java.util.List;

public interface Snake {
  List<Coordinate> getPosition();
  Direction getDirection();
}
