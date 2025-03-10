package util;

public class Functions {
    public static double clamp(double value, double min, double max) {
            return value < min ? min : value > max ? max : value;
    }

    public static Vector addVectors(Vector vector1, Vector vector2) {
        return new Vector(
            vector1.getX() + vector2.getX(), 
            vector1.getY() + vector2.getY(), 
            vector1.getZ() + vector2.getZ()
        );
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        return new Vector(
            vector1.getX() - vector2.getX(), 
            vector1.getY() - vector2.getY(), 
            vector1.getZ() - vector2.getZ()
        );
    }

    public static Vector scaleVector(Vector vector, double scalar) {
        return new Vector(
            vector.getX() * scalar, 
            vector.getY() * scalar, 
            vector.getZ() * scalar
        );
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();
    }

    public static Vector crossProduct(Vector vector1, Vector vector2) {
        double newX = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
        double newY = vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ();
        double newZ = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();
        return new Vector(newX, newY, newZ);
    }

    public static Vector normalize(Vector vector) {
        return scaleVector(vector, 1 / vector.getMagnitude());
    }

    public static double sqrt(double d) {
        return java.lang.Math.sqrt(d);
    }
}