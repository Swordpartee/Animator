package util;

public class Vector {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector(double x, double y, double z) {
        this(x, y, z, 1);
    }

    public Vector(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    public Vector() {
        this(0, 0, 0, 1);
    }


    public Vector(double x, double y) {
        this(x, y, 0, 1);
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = vector.w;
    }

    public Vector get() {
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setPose(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(Vector point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
        this.w = point.w;
    }

    public void move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void move(Vector point) {
        x += point.x;
        y += point.y;
        z += point.z;
    }

    public void moveX(double dx) {
        x += dx;
    }

    public void moveY(double dy) {
        y += dy;
    }

    public void moveZ(double dz) {
        z += dz;
    }

    public void add(Vector vector) {
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
        setZ(getZ() + vector.getZ());
    }

    public void add(double x, double y, double z) {
        setX(getX() + x);
        setY(getY() + y);
        setZ(getZ() + z);
    }

    public void subtract(Vector vector) {
        setX(getX() - vector.getX());
        setY(getY() - vector.getY());
        setZ(getZ() - vector.getZ());
    }

    public void subtract(double x, double y, double z) {
        setX(getX() - x);
        setY(getY() - y);
        setZ(getZ() - z);
    }
    
    public Vector scale(double scalar) {
        setX(getX() * scalar);
        setY(getY() * scalar);
        setZ(getZ() * scalar);
        return this;
    }

    public double getMagnitude() {
        return Functions.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector normalize() {
        return scale(1 / getMagnitude());
    }

    @Override
    public String toString() {
        return "Vector - (" + x + ", " + y + ", " + z + ")";
    }
}
