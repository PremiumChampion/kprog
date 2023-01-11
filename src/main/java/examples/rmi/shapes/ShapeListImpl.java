package examples.rmi.shapes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapeListImpl implements ShapeList {
  private static final Logger logger = LoggerFactory.getLogger(ShapeListImpl.class);
  private int version;
  private final List<Shape> theList;

  public ShapeListImpl() {
    theList = new ArrayList<Shape>();
  }

  public Shape newShape(GraphicalObject g) throws RemoteException {
    version++;
    logger.info("Ah, a new shape ..");
    // create a new shape object with the graphical object
    ShapeImpl ss = new ShapeImpl(g, version);
    // make the shape accessible
    Shape s = (Shape) UnicastRemoteObject.exportObject(ss, 0);
    // add the shape to the list of shapes
    theList.add(s);
    return s;
  }

  public List<Shape> allShapes() throws RemoteException {
    logger.info("here they come, {} shapes ...", theList.size());
    return theList;
  }

  public int getVersion() throws RemoteException {
    return version;
  }
}

