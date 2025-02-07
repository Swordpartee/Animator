package animation.animator;

import animation.animations.Animation;
import renderer.display.Camera;

public class AnimationCamera extends Camera {

    public AnimationCamera(float x, float y, float z, float tx, float ty, float tz, float fov, float aspect, float near, float far) {
        super(x, y, z, tx, ty, tz, fov, aspect, near, far);
    }

    public Animation moveTargetTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Camera - MoveTargetTo";
            };

            @Override
            public void generate() {
                float[] target = AnimationCamera.this.getTarget();

                for (int i = 0; i <= duration; i++) {
                    float dx = (x - target[0]) / duration;
                    float dy = (y - target[1]) / duration;
                    float dz = (z - target[2]) / duration;
                    this.addFrame(() -> {
                        AnimationCamera.this.moveTarget(dx, dy, dz);
                    });
                }
            }
        };
    }

    public Animation moveTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Camera - MoveTo";
            };

            @Override
            public void generate() {
                float[] target = AnimationCamera.this.getPosition();

                for (int i = 0; i <= duration; i++) {
                    float dx = (x - target[0]) / duration;
                    float dy = (y - target[1]) / duration;
                    float dz = (z - target[2]) / duration;
                    this.addFrame(() -> {
                        AnimationCamera.this.move(dx, dy, dz);
                    });
                }
            }
        };
    }
}
