package tools;

import animations.Animation;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AnimationItterator extends ScheduledThreadPoolExecutor {
    private final int frameDuration;

    public AnimationItterator(int corePoolSize, int frameDuration) {
        super(corePoolSize);
        this.frameDuration = frameDuration;
    }

    public void add(Animation animation) {
        Animator.getAnimations().add(animation);
    }

    private void runFrame() {
        Queue<Animation> animations = Animator.getAnimations();
        Runnable frame = animations.peek().nextFrame();
        if (frame != null) {
            frame.run();
        } else {
            animations.poll();
        }
        Animator.repaint();
    }

    public void start() {
        scheduleAtFixedRate(this::runFrame, 0, frameDuration, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}
