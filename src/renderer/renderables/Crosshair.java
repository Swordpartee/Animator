package renderer.renderables;

import java.awt.Color;
import renderer.display.RenderGraphic;
import util.Vector;

public class Crosshair implements Renderable {
    private int screenWidth;
    private int screenHeight;

    public Crosshair(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setScreenSize(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void paint(RenderGraphic g) {
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        int size = 10; // Size of the crosshair lines
        
        // Set color to white
        // g.setColor(Color.BLACK);

        // Draw lines
        g.drawLine2D(centerX - size, centerY, centerX + size, centerY, Color.BLACK);
        g.drawLine2D(centerX, centerY - size, centerX, centerY + size, Color.BLACK);
    }

    @Override
    public void setPos(double x, double y, double z) {
        // Not needed for crosshair
    }

    @Override
    public void setPos(Vector pos) {
        // Not needed for crosshair
    }

}
