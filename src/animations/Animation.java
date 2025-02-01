package animations;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Animation {
    private final Queue<Runnable> frames = new LinkedBlockingDeque<>();
    private boolean generated = false;

    public Animation() {
        addSelf();
    }

    protected void addFrame(Runnable frame) {
        frames.add(frame);
    }

    public Queue<Runnable> getFrames() {
        return frames;
    }

    public int getLength() {
        return frames.size();
    }

    protected final void addSelf() {
        // Animator.add(this);
    }

    public Runnable nextFrame() {
        if (!generated) {
            generate();
            generated = true;
        }
        return frames.poll();
    }

    public abstract void generate();

    @Override
    public abstract String toString();
}
