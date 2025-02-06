package renderer.display;

import java.awt.Canvas;
import java.awt.Graphics;

import animator.Animator;

public class RenderGraphic extends Canvas {
    @Override
    public void paint(Graphics g) {
        Animator.paint(g);
    }

}
