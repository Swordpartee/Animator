package simulator.physicsobjects;

import java.awt.Color;

import renderer.renderables.AxisBox;
import simulator.colliders.BoxCollider;
import simulator.physicsbodies.Rigidbody;
import util.Vector;

public class AxisCube {
    public Vector pos;
    public Vector size;
    private Color color;

    private final AxisBox sprite;
    private final Rigidbody body;

    public AxisCube(double x, double y, double z, double sizeX, double sizeY, double sizeZ, Color color) {
        pos = new Vector(x, y, z);
        size = new Vector(sizeX, sizeY, sizeZ);
        this.color = color;

        sprite = new AxisBox(pos, size, this.color);
        body = new Rigidbody(new BoxCollider(pos, size), 1);
    }
}
