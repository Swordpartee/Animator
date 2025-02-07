package animation.animations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelAnimation extends Animation {
    private final List<Animation> animations = new ArrayList<>();
    
    public ParallelAnimation(Animation ...animations) {
        this.animations.addAll(Arrays.asList(animations));
    }

    public int getMaxLength() {
        int maxlength = 0;
        for (Animation animation : animations) {
            if (animation.getLength() > maxlength) {
                maxlength = animation.getLength();
            }
        }
        return maxlength;
    }

    @Override
    public void generate() {
        animations.forEach(Animation::generate);

        int maxLength = getMaxLength();

        for (int i = 0; i < maxLength; i++) {
            addFrame(() -> {
                animations.forEach(animation -> {
                    Runnable frame = animation.getFrames().poll();
                    if (frame != null) {
                        frame.run();
                    }
                });
            });        
        }
        
    }

    @Override
    public String toString() {
        return "CompAnimation[" + animations + "]";
    }

}
