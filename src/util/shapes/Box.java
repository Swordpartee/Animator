package util.shapes;

import util.Vector;

public class Box extends Vector {
    private Vector size;

    public Box(Vector pos, Vector size) {
        super(pos);
        this.size = size;
    }

    public Box(double x, double y, double z, double width, double height, double depth) {
        this(new Vector(x, y, z), new Vector(width, height, depth));
    }

    public Box() {
        this(new Vector(), new Vector());
    }

    public Vector getSize() {
        return size;
    }

    public double getWidth() {
        return size.getX();
    }

    public double getHeight() {
        return size.getY();
    }

    public double getDepth() {
        return size.getZ();
    }

    public void setSize(Vector size) {
        this.size = size;
    }

    public void setSize(double width, double height, double depth) {
        size.set(width, height, depth);
    }

    public void setWidth(double width) {
        size.setX(width);
    }

    public void setHeight(double height) {
        size.setY(height);
    }

    public void setDepth(double depth) {
        size.setZ(depth);
    }

    public Vector getMin() {
        return new Vector(getX() - size.getX() / 2, getY() - size.getY() / 2, getZ() - size.getZ() / 2);
    }

    public Vector getMax() {
        return new Vector(getX() + size.getX() / 2, getY() + size.getY() / 2, getZ() + size.getZ() / 2);
    }
}
