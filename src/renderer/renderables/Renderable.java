package renderer.renderables;


import renderer.display.Camera;
import renderer.display.RenderGraphic;

public interface Renderable {
    public void paint(RenderGraphic graphics, Camera camera);
}
