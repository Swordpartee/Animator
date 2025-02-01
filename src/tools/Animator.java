package tools;

import animations.Animation;
import display.Display;
import drawables.Drawable;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Animator {
    public static final int FRAME_DURATION = 1000 / 60;

    private static final Display display = new Display();
    private static final AnimationItterator iterator = new AnimationItterator(1, FRAME_DURATION);
    private static final List<Drawable> drawables = new ArrayList<>();
    private static final Queue<Animation> animations = new LinkedBlockingDeque<>();

    public void config(boolean visible, int width, int height, String title) {
        display.config(visible, width, height, title);
    }

    public static void register(Drawable drawable) {
        drawables.add(drawable);
    }

    public static void add(Animation animation) {
        animations.add(animation);
    }

    public static void add(Animation ...animation) {
        animations.addAll(Arrays.asList(animation));
    }

    public static void dropAnimations(int n) {
        for (int i = 0; i < n; i++) {
            animations.poll();
        }
    }

    public static void start() {
        display.config(true, null, null, null);
        display.applyConfig();
        drawables.forEach(drawable -> System.out.println(drawable));
        animations.forEach(animation -> System.out.println(animation));
        iterator.start();
    }

    public static void repaint() {
        display.repaint();
    }

    public static void paint(Graphics g) {
        drawables.forEach(drawable -> drawable.paint(g));
    }

    public static Queue<Animation> getAnimations() {
        return animations;
    }
}
