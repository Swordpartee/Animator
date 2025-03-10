package util.shapes;

import util.Vector;

public class Sphere extends Vector {
    private double radius;

    public Sphere(Vector pos, double radius) {
        super(pos);
        this.radius = radius;
    }

    public Sphere(double x, double y, double z, double radius) {
        this(new Vector(x, y, z), radius);
    }

    public Sphere() {
        this(new Vector(), 1);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
