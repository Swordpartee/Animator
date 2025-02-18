package renderer.display;

import util.Point;
import util.Vector;

public class Camera {
    private final double[][] viewMatrix = new double[4][4];
    private final double[][] projectionMatrix = new double[4][4];
    private final Vector position;
    private final Vector target;
    private double fov;
    private double aspect;
    private double near;
    private double far;

    public Camera(double x, double y, double z, double targetX, double targetY, double targetZ, double fov, double aspect, double near, double far) {
        position = new Vector(x, y, z);
        target = new Vector(targetX, targetY, targetZ);
        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getTarget() {
        return target;
    }

    public void setFOV(double fov) {
        this.fov = fov;
    }

    public void setAspect(double aspect) {
        this.aspect = aspect;
    }

    public void setNear(double near) {
        this.near = near;
    }

    public void setFar(double far) {
        this.far = far;
    }

    private Point applyViewMatrix(Point coords) {
        Point result = new Point();
        result.setX(coords.getX() * viewMatrix[0][0] + coords.getY() * viewMatrix[0][1] + coords.getZ() * viewMatrix[0][2] + coords.getW() * viewMatrix[0][3]);
        result.setY(coords.getX() * viewMatrix[1][0] + coords.getY() * viewMatrix[1][1] + coords.getZ() * viewMatrix[1][2] + coords.getW() * viewMatrix[1][3]);
        result.setZ(coords.getX() * viewMatrix[2][0] + coords.getY() * viewMatrix[2][1] + coords.getZ() * viewMatrix[2][2] + coords.getW() * viewMatrix[2][3]);
        result.setW(coords.getX() * viewMatrix[3][0] + coords.getY() * viewMatrix[3][1] + coords.getZ() * viewMatrix[3][2] + coords.getW() * viewMatrix[3][3]);
        return result;
    }

    private Point applyProjectionMatrix(Point coords) {
        Point result = new Point();

        result.setX(coords.getX() * projectionMatrix[0][0] + coords.getY() * projectionMatrix[0][1] + coords.getZ() * projectionMatrix[0][2] + coords.getW() * projectionMatrix[0][3]);
        result.setY(coords.getX() * projectionMatrix[1][0] + coords.getY() * projectionMatrix[1][1] + coords.getZ() * projectionMatrix[1][2] + coords.getW() * projectionMatrix[1][3]);
        result.setZ(coords.getX() * projectionMatrix[2][0] + coords.getY() * projectionMatrix[2][1] + coords.getZ() * projectionMatrix[2][2] + coords.getW() * projectionMatrix[2][3]);
        result.setW(coords.getX() * projectionMatrix[3][0] + coords.getY() * projectionMatrix[3][1] + coords.getZ() * projectionMatrix[3][2] + coords.getW() * projectionMatrix[3][3]);
        return result;
    }

    public void update() {
        updateViewMatrix();
        updateProjectionMatrix();
    }

    private void updateViewMatrix() {
        Vector forward = new Vector();
        Vector up = new Vector(0, 1, 0);
        Vector right;
        Vector cameraUp;

        forward.setX(target.getX() - position.getX());
        forward.setY(target.getY() - position.getY());
        forward.setZ(target.getZ() - position.getZ());
        forward = forward.normalize();

        // Calculate right vector
        right = forward.crossProduct(up).normalize();

        // Calculate camera up vector
        cameraUp = forward.crossProduct(right).normalize();

        // Set view matrix
        viewMatrix[0][0] = right.getX();
        viewMatrix[1][0] = cameraUp.getX();
        viewMatrix[2][0] = -forward.getX();
        viewMatrix[0][1] = right.getY();
        viewMatrix[1][1] = cameraUp.getY();
        viewMatrix[2][1] = -forward.getY();
        viewMatrix[0][2] = right.getZ();
        viewMatrix[1][2] = cameraUp.getZ();
        viewMatrix[2][2] = -forward.getZ();

        viewMatrix[0][3] = -position.dotProduct(right);
        viewMatrix[1][3] = -position.dotProduct(cameraUp);
        viewMatrix[2][3] = position.dotProduct(forward);
        viewMatrix[3][3] = 1;
    }

    private void updateProjectionMatrix() {
        double tanHalfFov = Math.tan(Math.toRadians(fov / 2.0));

        projectionMatrix[0][0] = 1 / (aspect * tanHalfFov);
        projectionMatrix[1][1] = 1 / tanHalfFov;
        projectionMatrix[2][2] = -(far + near) / (far - near);
        projectionMatrix[2][3] = -1;
        projectionMatrix[3][2] = -(2 * far * near) / (far - near);
    }

    public Point convertTo2D(Point worldCoords) {
        // Apply view and projection matrices
        Point viewCoords = applyViewMatrix(worldCoords);
        Point clipCoords = applyProjectionMatrix(viewCoords);

        // Perform perspective divide
        Point ndcCoords = new Point();
        ndcCoords.setX(clipCoords.getX() / clipCoords.getW());
        ndcCoords.setY(clipCoords.getY() / clipCoords.getW());
        ndcCoords.setZ(clipCoords.getZ() / clipCoords.getW());

        // Convert to screen coordinates
        Point screenCoords = new Point();
        screenCoords.setX((ndcCoords.getX() * 400) + 400); // Assuming screen width is 800
        screenCoords.setY((ndcCoords.getY() * 400) + 300); // Assuming screen height is 600

        return screenCoords;
    }
}
