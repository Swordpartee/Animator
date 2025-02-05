package objects;

import java.awt.Graphics;

import display.Camera;
import tools.Animator;

public abstract class PhysicsObject {
    public PhysicsObject() {
        postInit();
    }

    private void postInit() {
        Animator.register(this);
    }

    public abstract void paint(Graphics g, Camera camera);
    
}
