package renderer.renderables;


import renderer.display.Camera;
import renderer.display.RenderGraphic;

public interface Renderable {
    public abstract void paint(RenderGraphic g, Camera camera);


    public abstract void setPos(float x, float y, float z);
}
