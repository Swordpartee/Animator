package display;

import java.awt.Canvas;
import java.awt.Graphics;

import tools.Animator;

public class AnimationScreen extends Canvas {
    @Override
    public void paint(Graphics g) {
        Animator.paint(g);
    }

}
