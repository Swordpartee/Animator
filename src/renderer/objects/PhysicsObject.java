package renderer.objects;

import java.awt.Graphics;

import animator.Animator;
import renderer.display.Camera;

public abstract class PhysicsObject {
    public PhysicsObject() {
        postInit();
    }

    private void postInit() {
        Animator.register(this);
    }

    public abstract void paint(Graphics g, Camera camera);
    
}
