package renderer.display;

public class Camera {
    private final float[][] viewMatrix = new float[4][4];
    private final float[][] projectionMatrix = new float[4][4];
    private final float[] position = new float[3];
    private final float[] target = new float[3];
    private float fov = 60.0f; // Field of view
    private float aspect = 800.0f / 600.0f; // Aspect ratio
    private float near = 0.1f; // Near clipping plane
    private float far = 1000.0f; // Far clipping plane

    public Camera(float x, float y, float z, float targetX, float targetY, float targetZ, float fov, float aspect, float near, float far) {
        this.position[0] = x;
        this.position[1] = y;
        this.position[2] = z;
        this.target[0] = targetX;
        this.target[1] = targetY;
        this.target[2] = targetZ;
        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
    }

    public float[] getPosition() {
        return position;
    }

    public float[] getTarget() {
        return target;
    }

    public void move(float dx, float dy, float dz) {
        this.position[0] += dx;
        this.position[1] += dy;
        this.position[2] += dz;
    }

    public void moveTarget(float dx, float dy, float dz) {
        this.target[0] += dx;
        this.target[1] += dy;
        this.target[2] += dz;
    }

    public float[] applyViewTransformation(float[] coords) {
        updateViewMatrix();
        float[] result = new float[4];
        for (int i = 0; i < 4; i++) {
            result[i] = coords[0] * viewMatrix[i][0] + coords[1] * viewMatrix[i][1] + coords[2] * viewMatrix[i][2] + viewMatrix[i][3];
        }
        return result;
    }

    public void updateViewMatrix() {
        float[] forward = new float[3];
        float[] up = {0, 1, 0};
        float[] right = new float[3];
        float[] cameraUp = new float[3];

        // Calculate forward vector
        for (int i = 0; i < 3; i++) {
            forward[i] = target[i] - position[i];
        }
        normalize(forward);

        // Calculate right vector
        crossProduct(up, forward, right);
        normalize(right);

        // Calculate camera up vector
        crossProduct(forward, right, cameraUp);

        // Set view matrix
        for (int i = 0; i < 3; i++) {
            viewMatrix[0][i] = right[i];
            viewMatrix[1][i] = cameraUp[i];
            viewMatrix[2][i] = -forward[i];
        }
        viewMatrix[0][3] = -dotProduct(right, position);
        viewMatrix[1][3] = -dotProduct(cameraUp, position);
        viewMatrix[2][3] = dotProduct(forward, position);
        viewMatrix[3][3] = 1.0f;
    }

    public void updateProjectionMatrix() {
        float tanHalfFov = (float) Math.tan(Math.toRadians(fov / 2.0));

        projectionMatrix[0][0] = 1.0f / (aspect * tanHalfFov);
        projectionMatrix[1][1] = 1.0f / tanHalfFov;
        projectionMatrix[2][2] = -(far + near) / (far - near);
        projectionMatrix[2][3] = -1.0f;
        projectionMatrix[3][2] = -(2.0f * far * near) / (far - near);
    }

    private void normalize(float[] v) {
        float length = (float) Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        for (int i = 0; i < 3; i++) {
            v[i] /= length;
        }
    }

    private void crossProduct(float[] a, float[] b, float[] result) {
        result[0] = a[1] * b[2] - a[2] * b[1];
        result[1] = a[2] * b[0] - a[0] * b[2];
        result[2] = a[0] * b[1] - a[1] * b[0];
    }

    private float dotProduct(float[] a, float[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }

    public float[] convertTo2D(float[] worldCoords) {
        float[] viewCoords = applyViewTransformation(worldCoords);
        float[] clipCoords = new float[4];
        for (int i = 0; i < 4; i++) {
            clipCoords[i] = viewCoords[0] * projectionMatrix[i][0] + viewCoords[1] * projectionMatrix[i][1] + viewCoords[2] * projectionMatrix[i][2] + projectionMatrix[i][3];
        }
        // Perform perspective divide
        float[] ndcCoords = new float[3];
        ndcCoords[0] = clipCoords[0] / clipCoords[3];
        ndcCoords[1] = clipCoords[1] / clipCoords[3];
        ndcCoords[2] = clipCoords[2] / clipCoords[3];

        // Convert to screen coordinates
        float[] screenCoords = new float[2];
        screenCoords[0] = (ndcCoords[0] + 1) * 0.5f * 800; // Assuming screen width is 800
        screenCoords[1] = (1 - ndcCoords[1]) * 0.5f * 600; // Assuming screen height is 600

        return screenCoords;
    }

    public boolean isVisible(float[] worldCoords) {
        // float[] viewCoords = applyViewTransformation(worldCoords);
        // float z = viewCoords[2]; // Z-coordinate in view space
    
        // // Check if the object is within the near and far planes
        // if (z < near || z > far) {
        //     return false;
        // }
    
        // // Check if the object is within the view frustum (left, right, top, bottom planes)
        // float[] clipCoords = new float[4];
        // for (int i = 0; i < 4; i++) {
        //     clipCoords[i] = viewCoords[0] * projectionMatrix[i][0] + viewCoords[1] * projectionMatrix[i][1] + viewCoords[2] * projectionMatrix[i][2] + projectionMatrix[i][3];
        // }
    
        // // Perform perspective divide
        // float[] ndcCoords = new float[3];
        // ndcCoords[0] = clipCoords[0] / clipCoords[3];
        // ndcCoords[1] = clipCoords[1] / clipCoords[3];
        // ndcCoords[2] = clipCoords[2] / clipCoords[3];
    
        // // Check if the object is within the normalized device coordinates (NDC) range [-1, 1]
        // return ndcCoords[0] >= -1 && ndcCoords[0] <= 1 && ndcCoords[1] >= -1 && ndcCoords[1] <= 1 && ndcCoords[2] >= -1 && ndcCoords[2] <= 1;

        return true;
    }

}
