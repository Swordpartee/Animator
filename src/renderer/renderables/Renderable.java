package renderer.renderables;

import renderer.display.RenderGraphic;
import util.Vector;

public interface Renderable {
    public void paint(RenderGraphic graphics);

    public void setPos(double x, double y, double z);

    public void setPos(Vector pos);
}
