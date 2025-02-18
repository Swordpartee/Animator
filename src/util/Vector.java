package util;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Vector(Point point) {
        super(point.getX(), point.getY(), point.getZ());
    }

    public Vector() {
        super();
    }

    public Vector add(Vector vector) {
        return new Vector(getX() + vector.getX(), getY() + vector.getY(), getZ() + vector.getZ());
    }

    public Vector subtract(Vector vector) {
        return new Vector(getX() - vector.getX(), getY() - vector.getY(), getZ() - vector.getZ());
    }
    
    public Vector scale(double scalar) {
        return new Vector(getX() * scalar, getY() * scalar, getZ() * scalar);
    }

    public double dotProduct(Vector other) {
        return getX() * other.getX() + getY() * other.getY() + getZ() * other.getZ();
    }

    public Vector crossProduct(Vector other) {
        return new Vector(getY() * other.getZ() - getZ() * other.getY(), getZ() * other.getX() - getX() * other.getZ(), getX() * other.getY() - getY() * other.getX());
    }

    public double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector normalize() {
        return scale(1 / magnitude());
    }
}
