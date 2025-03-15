package renderer.renderables;

import java.awt.Color;
import renderer.display.RenderGraphic;
import util.Vector;

public class DrawVector extends Vector implements Renderable {
    Color color;

    public DrawVector(double x, double y, double z, Color color) {
        super(x, y, z);

        this.color = color;
    }

    public DrawVector(Vector v, Color color) {
        super(v);

        this.color = color;
    }

    @Override
    public void paint(RenderGraphic graphics) {
        graphics.setColor(color);
        graphics.drawLine(new Vector(), this);
    }

}
