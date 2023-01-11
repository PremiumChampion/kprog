package examples.rmi.shapes;

import java.rmi.RemoteException;


public class ShapeImpl implements Shape {
  private final GraphicalObject object;
  private final int version;

  public ShapeImpl(final GraphicalObject object, final int version) {
    this.object = object;
    this.version = version;
  }

  public final int getVersion() throws RemoteException {
    return version;
  }

  public final GraphicalObject getAllState() throws RemoteException {
    return object;
  }

}

