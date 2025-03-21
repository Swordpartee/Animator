package util;

import java.awt.Point;

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

    public Vector(Point point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = 0;
        this.w = 1;
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

    public Vector setX(double x) {
        this.x = x;
        
        return this;
    }

    public Vector setY(double y) {
        this.y = y;
        
        return this;
    }

    public Vector setZ(double z) {
        this.z = z;
        
        return this;
    }

    public Vector setW(double w) {
        this.w = w;
        
        return this;
    }

    public Vector set(double x, double y) {
        this.x = x;
        this.y = y;
        
        return this;
    }

    public Vector set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        return this;
    }

    public Vector set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        
        return this;
    }

    public Vector set(Vector point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
        this.w = point.w;
        
        return this;
    }

    public Vector move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
        
        return this;
    }

    public Vector move(Vector point) {
        x += point.x;
        y += point.y;
        z += point.z;
        
        return this;
    }

    public Vector moveX(double dx) {
        x += dx;
        
        return this;
    }

    public Vector moveY(double dy) {
        y += dy;
        
        return this;
    }

    public Vector moveZ(double dz) {
        z += dz;
        
        return this;
    }

    public Vector add(Vector vector) {
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
        setZ(getZ() + vector.getZ());
        
        return this;
    }

    public Vector add(double x, double y, double z) {
        setX(getX() + x);
        setY(getY() + y);
        setZ(getZ() + z);
        
        return this;
    }

    public Vector subtract(Vector vector) {
        setX(getX() - vector.getX());
        setY(getY() - vector.getY());
        setZ(getZ() - vector.getZ());
        
        return this;
    }

    public Vector subtract(double x, double y, double z) {
        setX(getX() - x);
        setY(getY() - y);
        setZ(getZ() - z);
        
        return this;
    }
    
    public Vector scale(double scalar) {
        setX(getX() * scalar);
        setY(getY() * scalar);
        setZ(getZ() * scalar);

        return this;
    }

    public Vector scale(double scalarX, double scalarY, double scalarZ) {
        setX(getX() * scalarX);
        setY(getY() * scalarY);
        setZ(getZ() * scalarZ);

        return this;
    }

    public Vector scale(Vector scalar) {
        setX(getX() * scalar.getX());
        setY(getY() * scalar.getY());
        setZ(getZ() * scalar.getZ());

        return this;
    }

    public Vector scaleX(double scalar) {
        setX(getX() * scalar);

        return this;
    }
    
    public Vector scaleY(double scalar) {
        setY(getY() * scalar);

        return this;
    }

    public Vector scaleZ(double scalar) {
        setZ(getZ() * scalar);

        return this;
    }

    public double getMagnitude() {
        return Functions.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector normalize() {
        return scale(1 / getMagnitude());
    }

    /**
     * Calculate the Euclidean distance between this vector and another vector.
     * @param other The other vector to calculate distance to
     * @return The distance between the two vectors
     */
    public double distance(Vector other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public String toString() {
        return "Vector - (" + x + ", " + y + ", " + z + ")";
    }
}
