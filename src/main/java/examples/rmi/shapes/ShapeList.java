package examples.rmi.shapes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ShapeList extends Remote {
  // Constant for registry lookup
  String REGISTRY_NAME = "shapes.shapes.Shape List";

  // add a new graphical object to the whiteboard
  Shape newShape(GraphicalObject g) throws RemoteException;

  // get all shapes
  List<Shape> allShapes() throws RemoteException;

  // get the latest version number
  int getVersion() throws RemoteException;
}
