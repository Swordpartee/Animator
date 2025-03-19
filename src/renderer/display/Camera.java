package renderer.display;

import util.Functions;
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

    public Camera(double x, double y, double z, double targetX, double targetY, double targetZ, double fov,
            double aspect, double near, double far) {
        position = new Vector(x, y, z);
        target = new Vector(targetX, targetY, targetZ);
        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
    }

    public Camera() {
        this(10, 10, 10, 0, 0, 0, 90, 1, 0.1, 1000);
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

    // public boolean inFrame(Vector coords) {
    //     if (coords.getZ() <= near || coords.getZ() >= far) {
    //         return false;
    //     }

    //     // return coords.getX() >= -400 && coords.getX() <= 400 &&
    //     //         coords.getY() >= -300 && coords.getY() <= 300;

    //     return true;

    // }

    // Add this method to your Camera class
    public boolean isInFrontOfCamera(Vector point) {
        Vector viewVector = Functions.subtractVectors(target, position).normalize();
        Vector pointVector = Functions.subtractVectors(point, position);

        // Calculate dot product to determine if point is in front of camera
        double dotProduct = Functions.getDotProduct(viewVector, pointVector);

        // If dot product is positive, the point is in front of camera
        return dotProduct > 0;
    }

    private Vector applyViewMatrix(Vector coords) {
        Vector result = new Vector();
        result.setX(coords.getX() * viewMatrix[0][0] + coords.getY() * viewMatrix[0][1]
                + coords.getZ() * viewMatrix[0][2] + coords.getW() * viewMatrix[0][3]);
        result.setY(coords.getX() * viewMatrix[1][0] + coords.getY() * viewMatrix[1][1]
                + coords.getZ() * viewMatrix[1][2] + coords.getW() * viewMatrix[1][3]);
        result.setZ(coords.getX() * viewMatrix[2][0] + coords.getY() * viewMatrix[2][1]
                + coords.getZ() * viewMatrix[2][2] + coords.getW() * viewMatrix[2][3]);
        result.setW(coords.getX() * viewMatrix[3][0] + coords.getY() * viewMatrix[3][1]
                + coords.getZ() * viewMatrix[3][2] + coords.getW() * viewMatrix[3][3]);
        return result;
    }

    private Vector applyProjectionMatrix(Vector coords) {
        Vector result = new Vector();
        result.setX(coords.getX() * projectionMatrix[0][0] + coords.getY() * projectionMatrix[0][1]
                + coords.getZ() * projectionMatrix[0][2] + coords.getW() * projectionMatrix[0][3]);
        result.setY(coords.getX() * projectionMatrix[1][0] + coords.getY() * projectionMatrix[1][1]
                + coords.getZ() * projectionMatrix[1][2] + coords.getW() * projectionMatrix[1][3]);
        result.setZ(coords.getX() * projectionMatrix[2][0] + coords.getY() * projectionMatrix[2][1]
                + coords.getZ() * projectionMatrix[2][2] + coords.getW() * projectionMatrix[2][3]);
        result.setW(coords.getX() * projectionMatrix[3][0] + coords.getY() * projectionMatrix[3][1]
                + coords.getZ() * projectionMatrix[3][2] + coords.getW() * projectionMatrix[3][3]);
        return result;
    }

    private Vector applyInverseViewMatrix(Vector coords) {
        Vector result = new Vector();
        result.setX(coords.getX() * viewMatrix[0][0] + coords.getY() * viewMatrix[1][0]
                + coords.getZ() * viewMatrix[2][0] + coords.getW() * viewMatrix[3][0]);
        result.setY(coords.getX() * viewMatrix[0][1] + coords.getY() * viewMatrix[1][1]
                + coords.getZ() * viewMatrix[2][1] + coords.getW() * viewMatrix[3][1]);
        result.setZ(coords.getX() * viewMatrix[0][2] + coords.getY() * viewMatrix[1][2]
                + coords.getZ() * viewMatrix[2][2] + coords.getW() * viewMatrix[3][2]);
        result.setW(coords.getX() * viewMatrix[0][3] + coords.getY() * viewMatrix[1][3]
                + coords.getZ() * viewMatrix[2][3] + coords.getW() * viewMatrix[3][3]);
        return result;
    }

    private Vector applyInverseProjectionMatrix(Vector coords) {
        Vector result = new Vector();
        result.setX(coords.getX() * projectionMatrix[0][0] + coords.getY() * projectionMatrix[1][0]
                + coords.getZ() * projectionMatrix[2][0] + coords.getW() * projectionMatrix[3][0]);
        result.setY(coords.getX() * projectionMatrix[0][1] + coords.getY() * projectionMatrix[1][1]
                + coords.getZ() * projectionMatrix[2][1] + coords.getW() * projectionMatrix[3][1]);
        result.setZ(coords.getX() * projectionMatrix[0][2] + coords.getY() * projectionMatrix[1][2]
                + coords.getZ() * projectionMatrix[2][2] + coords.getW() * projectionMatrix[3][2]);
        result.setW(coords.getX() * projectionMatrix[0][3] + coords.getY() * projectionMatrix[1][3]
                + coords.getZ() * projectionMatrix[2][3] + coords.getW() * projectionMatrix[3][3]);
        return result;
    }

    public void update() {
        updateViewMatrix();
        updateProjectionMatrix();
    }

    private void updateViewMatrix() {
        Vector up = new Vector(0, 1, 0);

        Vector forward = Functions.subtractVectors(target, position).normalize();

        Vector right = Functions.crossProduct(forward, up).normalize();

        Vector cameraUp = Functions.crossProduct(forward, right).normalize();

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

        viewMatrix[0][3] = -Functions.getDotProduct(position, right);
        viewMatrix[1][3] = -Functions.getDotProduct(position, cameraUp);
        viewMatrix[2][3] = Functions.getDotProduct(position, forward);

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

    public Vector toScreenCoords(Vector worldCoords) {
        // Apply view and projection matrices
        Vector viewCoords = applyViewMatrix(worldCoords);
        Vector clipCoords = applyProjectionMatrix(viewCoords);

        // Perform perspective divide
        Vector ndcCoords = new Vector();
        ndcCoords.setX(clipCoords.getX() / clipCoords.getW());
        ndcCoords.setY(clipCoords.getY() / clipCoords.getW());
        ndcCoords.setZ(clipCoords.getZ() / clipCoords.getW());

        // Convert to screen coordinates
        Vector screenCoords = new Vector();
        screenCoords.setX((ndcCoords.getX() * 400) + 400); // Assuming screen width is 800
        screenCoords.setY((ndcCoords.getY() * 400) + 300); // Assuming screen height is 600
        screenCoords.setZ(ndcCoords.getZ());

        return screenCoords;
    }

    public Vector toScreenCoords(double x, double y, double z) {
        return toScreenCoords(new Vector(x, y, z));
    }

    public Vector toWorldCoords(Vector screenCoords, double distance) {
        // Convert to normalized device coordinates
        Vector ndcCoords = new Vector();
        ndcCoords.setX((screenCoords.getX() - 400) / 400); // Assuming screen width is 800
        ndcCoords.setY((screenCoords.getY() - 300) / 300); // Assuming screen height is 600
        ndcCoords.setZ(distance);

        // Apply inverse projection and view matrices
        Vector viewCoords = applyInverseProjectionMatrix(ndcCoords);
        Vector worldCoords = applyInverseViewMatrix(viewCoords);

        return worldCoords;
    }

    public Vector toWorldCoords(double x, double y, double distance) {
        return toWorldCoords(new Vector(x, y), distance);
    }
}
