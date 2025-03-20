package renderer.display;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import util.Functions;
import util.Vector;

public class RenderGraphic {
    private final Graphics graphics;
    private final Camera camera;
    
    // Screen dimensions
    private final int width;
    private final int height;
    
    // Color buffer (what will be drawn to the screen)
    private final Color[][] colorBuffer;
    
    // Depth buffer (z-values for depth testing)
    private final double[][] depthBuffer;
    
    // Image for efficient rendering
    private final BufferedImage frameBuffer;
    
    /**
     * Create a new RenderGraphic with depth buffer
     */
    public RenderGraphic(Graphics g, Camera camera) {
        this.graphics = g;
        this.camera = camera;
        
        // Default to 800x600 if not specified
        this.width = 800;
        this.height = 600;
        
        // Initialize buffers
        this.colorBuffer = new Color[height][width];
        this.depthBuffer = new double[height][width];
        this.frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Initialize with default values
        clear();
    }
    
    /**
     * Alternate constructor with custom dimensions
     */
    public RenderGraphic(Graphics g, Camera camera, int width, int height) {
        this.graphics = g;
        this.camera = camera;
        this.width = width;
        this.height = height;
        
        // Initialize buffers
        this.colorBuffer = new Color[height][width];
        this.depthBuffer = new double[height][width];
        this.frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Initialize with default values
        clear();
    }

    /**
     * Get the underlying graphics object
     */
    public Graphics getGraphics() {
        return graphics;
    }
    
    /**
     * Clear color and depth buffers
     */
    private void clear() {
        // Set all pixels to black
        for (int y = 0; y < height; y++) {
            Arrays.fill(colorBuffer[y], Color.WHITE);
        }

        // Set all depths to maximum value (far away)
        for (int y = 0; y < height; y++) {
            Arrays.fill(depthBuffer[y], Double.MAX_VALUE);
        }
    }
    
    /**
     * Draw a line between two 3D points
     */
    public void drawLine(Vector p1, Vector p2) {
        boolean p1Visible = camera.isInFrontOfCamera(p1);
        boolean p2Visible = camera.isInFrontOfCamera(p2);
        
        // If one point is behind camera, clip the line to the camera plane
        if (!p1Visible || !p2Visible) {
            // We need to calculate the intersection with the camera's near plane
            Vector viewDirection = Functions.subtractVectors(camera.getTarget(), camera.getPosition()).normalize();
            double nearDistance = 0.1; // This should match your camera's near plane
            
            // Equation of near plane: dot(p - cameraPos, viewDirection) = nearDistance
            // Parametric line equation: p = p1 + t * (p2 - p1)
            // Solve for t where line intersects plane
            
            Vector lineDirection = Functions.subtractVectors(p2, p1);
            double denominator = Functions.getDotProduct(lineDirection, viewDirection);
            
            // If line is parallel to near plane, skip (shouldn't happen if one point is behind)
            if (Math.abs(denominator) < 0.0001) {
                return;
            }
            
            Vector cameraToPt = Functions.subtractVectors(p1, camera.getPosition());
            double t = (nearDistance - Functions.getDotProduct(cameraToPt, viewDirection)) / denominator;
            
            // Ensure t is between 0 and 1 (clipping the line segment)
            if (t < 0 || t > 1) {
                return;
            }
            
            // Calculate intersection point
            Vector intersection = new Vector(
                p1.getX() + t * lineDirection.getX(),
                p1.getY() + t * lineDirection.getY(),
                p1.getZ() + t * lineDirection.getZ()
            );
            
            // Replace the behind-camera point with the intersection
            if (!p1Visible) {
                p1 = intersection;
            } else {
                p2 = intersection;
            }
        }
        
        // Convert 3D points to screen coordinates
        Vector screenP1 = camera.toScreenCoords(p1);
        Vector screenP2 = camera.toScreenCoords(p2);
        
        // Use Bresenham's line algorithm with z-interpolation
        drawLineBresenham(
            (int)screenP1.getX(), (int)screenP1.getY(), screenP1.getZ(),
            (int)screenP2.getX(), (int)screenP2.getY(), screenP2.getZ(),
            graphics.getColor()
        );
    }
    
    /**
     * Bresenham's line algorithm with z-interpolation
     */
    private void drawLineBresenham(int x1, int y1, double z1, int x2, int y2, double z2, Color color) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        
        // Total distance for z-interpolation
        double totalDist = Math.sqrt(dx*dx + dy*dy);
        if (totalDist == 0) totalDist = 1; // Avoid division by zero
        
        // Current position
        int x = x1;
        int y = y1;
        double z;
        
        while (true) {
            // Skip if out of bounds
            if (x >= 0 && y >= 0 && x < width && y < height) {
                // Interpolate z-value
                double t = Math.sqrt((x-x1)*(x-x1) + (y-y1)*(y-y1)) / totalDist;
                z = (1-t) * z1 + t * z2;
                
                // Depth test - only draw if this point is closer than what's already there
                if (z < depthBuffer[y][x]) {
                    depthBuffer[y][x] = z;
                    colorBuffer[y][x] = color;
                }
            }
            
            // Exit condition
            if (x == x2 && y == y2) break;
            
            // Update position
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
    
    /**
     * Draw a point at the specified 3D position
     */
    public void drawPoint(Vector p, int size, Color color) {
        // Skip if point is behind camera
        if (!camera.isInFrontOfCamera(p)) {
            return;
        }
        
        // Convert to screen coordinates
        Vector screenP = camera.toScreenCoords(p);
        int x = (int)screenP.getX();
        int y = (int)screenP.getY();
        double z = screenP.getZ();
        
        // Draw point as a small square
        for (int dy = -size/2; dy <= size/2; dy++) {
            for (int dx = -size/2; dx <= size/2; dx++) {
                int px = x + dx;
                int py = y + dy;
                
                if (px >= 0 && py >= 0 && px < width && py < height) {
                    if (z < depthBuffer[py][px]) {
                        depthBuffer[py][px] = z;
                        colorBuffer[py][px] = color;
                    }
                }
            }
        }
    }
    
    /**
     * Set the current drawing color
     */
    public void setColor(Color color) {
        graphics.setColor(color);
    }
    
    /**
     * Render the color buffer to the screen
     */
    public void render() {
        // Transfer color buffer to image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (colorBuffer[y][x] != null) {
                    frameBuffer.setRGB(x, y, colorBuffer[y][x].getRGB());
                }
            }
        }
        
        // Draw the image to the graphics
        graphics.drawImage(frameBuffer, 0, 0, null);
    }
    
    /**
     * Draw a 2D line directly to the buffer (no depth testing)
     */
    public void drawLine2D(int x1, int y1, int x2, int y2, Color color) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int x = x1;
        int y = y1;

        while (true) {
            if (x >= 0 && y >= 0 && x < width && y < height) {
                colorBuffer[y][x] = color;
            }

            if (x == x2 && y == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
    /**
     * Draw text at the specified position
     */
    public void drawString(String text, int x, int y) {
        graphics.drawString(text, x, y);
    }
}
