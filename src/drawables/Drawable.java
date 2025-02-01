package drawables;

import java.awt.Graphics;
import tools.Animator;

public abstract class Drawable {
    public Drawable() {
        postInit();
    }

    private void postInit() {
        Animator.register(this);
    }

    public abstract void paint(Graphics g);
    
}
