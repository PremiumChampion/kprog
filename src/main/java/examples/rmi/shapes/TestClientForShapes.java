package examples.rmi.shapes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class TestClientForShapes {

  public static void main(String[] args) {
    String host = (args.length < 1) ? null : args[0];

    try {
      Registry registry = LocateRegistry.getRegistry(host);
      ShapeList shapeListProxy = (ShapeList) registry.lookup(ShapeList.REGISTRY_NAME);

      // add first circle
      Circle circle1 = new Circle(17);
      Shape circleShape1 = shapeListProxy.newShape(circle1);
      System.out.println("Version of shapes.shapes.Circle = " + circleShape1.getVersion());

      // add second circle
      Circle circle2 = new Circle(44);
      Shape circleShape2 = shapeListProxy.newShape(circle2);
      System.out.println("Version of shapes.shapes.Circle = " + circleShape2.getVersion());

      // get all shapes and print them
      List<Shape> sList = shapeListProxy.allShapes();
      for (Shape shape : sList) {
        System.out.println(shape.getAllState());
      }
    } catch (RemoteException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.println("Client: " + e.getMessage());
    }
  }

}

