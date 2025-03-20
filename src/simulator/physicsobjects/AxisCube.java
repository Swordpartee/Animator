// package simulator.physicsobjects;

// import java.awt.Color;
// import renderer.display.RenderGraphic;

// import renderer.renderables.AxisBox;
// import simulator.colliders.BoxCollider;
// import simulator.physicsbodies.Rigidbody;
// import util.Vector;

// public class AxisCube implements PhysicsObject {
//     public Vector pos;
//     public Vector size;

//     private final AxisBox sprite;
//     private final Rigidbody body;

//     public AxisCube(double x, double y, double z, double sizeX, double sizeY, double sizeZ, Color color) {
//         pos = new Vector(x, y, z);
//         size = new Vector(sizeX, sizeY, sizeZ);

//         sprite = new AxisBox(pos, size, color);
//         body = new Rigidbody(new BoxCollider(pos, size), 1);
//     }

//     @Override
//     public Rigidbody getBody() {
//         return body;
//     }

//     @Override
//     public void paint(RenderGraphic graphics) {
//         sprite.paint(graphics);
//     }

// }
