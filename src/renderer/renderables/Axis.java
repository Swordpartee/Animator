package renderer.renderables;

import java.awt.Color;

import renderer.display.Camera;
import renderer.display.RenderGraphic;
import util.Vector;

public class Axis implements Renderable {
    private double length;
    
    public Axis(double length) {
        this.length = length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public void paint(RenderGraphic g, Camera camera) {
        Vector origin = new Vector();
        Vector x = new Vector(length, 0, 0);
        Vector y = new Vector(0, length, 0);
        Vector z = new Vector(0, 0, length);

        g.setColor(Color.RED);
        g.drawLine(origin, x, camera);
        g.setColor(Color.GREEN);
        g.drawLine(origin, y, camera);
        g.setColor(Color.BLUE);
        g.drawLine(origin, z, camera);
    }
}
