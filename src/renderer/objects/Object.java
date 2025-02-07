package renderer.objects;

import java.awt.Graphics;

import renderer.display.Camera;

public interface Object {
    public abstract void paint(Graphics g, Camera camera);

}
