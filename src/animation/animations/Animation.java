package animation.animations;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class Animation {
    private final Queue<Runnable> frames = new LinkedBlockingDeque<>();
    private boolean generated = false;

    protected void addFrame(Runnable frame) {
        frames.add(frame);
    }

    public Queue<Runnable> getFrames() {
        return frames;
    }

    public int getLength() {
        return frames.size();
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
