package animator;

import animations.Animation;
import renderer.display.Renderer;
import renderer.objects.PhysicsObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Animator {
    public static final int FRAME_DURATION = 1000 / 60;

    public static final Renderer display = new Renderer();
    private static final AnimationItterator iterator = new AnimationItterator(1, FRAME_DURATION);
    private static final List<PhysicsObject> physicsObjects = new ArrayList<>();
    private static final Queue<Animation> animations = new LinkedBlockingDeque<>();

    public void config(boolean visible, int width, int height, String title) {
        display.config(visible, width, height, title);
    }

    public static void register(PhysicsObject physicsObject) {
        physicsObjects.add(physicsObject);
    }

    public static void add(Animation animation) {
        animations.add(animation);
    }

    public static void add(Animation... animation) {
        animations.addAll(Arrays.asList(animation));
    }

    public static void dropAnimations(int n) {
        for (int i = 0; i < n; i++) {
            animations.poll();
        }
    }

    public Animation moveCameraTo(float x, float y, float z, int duration) {
        return display.moveCameraTo(x, y, z, duration);
    }

    public Animation moveCameraTargetTo(float x, float y, float z, int duration) {
        return display.moveCameraTargetTo(x, y, z, duration);
    }

    public static void start() {
        display.config(true, null, null, null);
        display.applyConfig();
        physicsObjects.forEach(physicsObject -> System.out.println(physicsObject));
        animations.forEach(animation -> System.out.println(animation));
        iterator.start();
    }

    public static void repaint() {
        display.repaint();
    }

    public static void paint(Graphics g) {
        display.getCamera().updateProjectionMatrix();

        g.setColor(Color.RED);
        int centerX = display.getWidth() / 2;
        int centerY = display.getHeight() / 2;

        g.drawLine(centerX, centerY, (int) display.getCamera().convertTo2D(new float[]{5, 0, 0})[0], (int) display.getCamera().convertTo2D(new float[]{5, 0, 0})[1]);

        g.setColor(Color.GREEN);
        g.drawLine(centerX, centerY, (int) display.getCamera().convertTo2D(new float[]{0, 5, 0})[0], (int) display.getCamera().convertTo2D(new float[]{0, 5, 0})[1]);

        g.setColor(Color.BLUE);
        g.drawLine(centerX, centerY, (int) display.getCamera().convertTo2D(new float[]{0, 0, 5})[0], (int) display.getCamera().convertTo2D(new float[]{0, 0, 5})[1]);

        physicsObjects.forEach(physicsObject -> physicsObject.paint(g, display.getCamera()));
    }

    public static Queue<Animation> getAnimations() {
        return animations;
    }
}
