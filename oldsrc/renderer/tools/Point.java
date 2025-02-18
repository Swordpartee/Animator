package renderer.tools;

import java.util.List;

public class Point {
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
        this(0, 0, 0);
    }

    private int x;
    private int y;
    private int z;

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public void x(int x) {
        this.x = x;
    }

    public void y(int y) {
        this.y = y;
    }

    public void z(int z) {
        this.z = z;
    }

    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override 
    public String toString() {
        return (x + " " + y + " " + z);
    }

}
