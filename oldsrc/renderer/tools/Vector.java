package renderer.tools;

public class Vector extends Point {
    public Vector(int x, int y, int z) {
        super(x, y, z);
    }

    public Vector() {
        this(0, 0, 0);
    }

    public void normalize() {
        float length = (float) Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        for (int i = 0; i < 3; i++) {
            v[i] /= length;
        }
    }

    public void crossProduct(float[] a, float[] b, float[] result) {
        result[0] = a[1] * b[2] - a[2] * b[1];
        result[1] = a[2] * b[0] - a[0] * b[2];
        result[2] = a[0] * b[1] - a[1] * b[0];
    }

    public float dotProduct(Vector ) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }
}
