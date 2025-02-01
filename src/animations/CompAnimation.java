package animations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompAnimation extends Animation {
    private final List<Animation> animations = new ArrayList<>();
    
    public CompAnimation(Animation ...animations) {
        this.animations.addAll(Arrays.asList(animations));

        addSelf();
    }

    @Override
    public void generate() {
        int maxlength = 0;

        animations.forEach(Animation::generate);

        for (Animation animation : animations) {
            if (animation.getLength() > maxlength) {
                maxlength = animation.getLength();
            }
        }

        for (int i = 0; i < maxlength; i++) {
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
