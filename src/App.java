import animation.animations.ParallelAnimation;
import animation.animator.Animator;
import animation.objects.AnimationCube;
import java.awt.Color;

public class App {
    public static AnimationCube rect1;

    public static void main(String[] args) throws Exception {
        rect1 = new AnimationCube(-10, -10, -10, 3, 3, 3, Color.BLACK);

        Animator.register(rect1);

        Animator.add(rect1.moveTo(-15, -15, -10, 60));

        Animator.add(Animator.moveCameraTo(50, 50, 50, 60));
        
        Animator.add(rect1.moveTo(0, 0, 0, 60));

        Animator.add(rect1.scaleTo(3, 3, 6, 60));

        Animator.add(rect1.scaleTo(3, 3, 3, 60));

        Animator.add(Animator.moveCameraTo(50, 0, 0, 60));

        Animator.add(rect1.moveTo(5, 0, 0, 60));

        Animator.add(rect1.moveTo(0, 5, 0, 60));

        Animator.add(rect1.moveTo(0, 0, 5, 60));

        Animator.add(rect1.scaleTo(3, 3, 0, 60));

        Animator.add(new ParallelAnimation(
            Animator.moveCameraTo(50, 50, 0, 60),
            rect1.moveTo(0, 0, 0, 60), 
            rect1.scaleTo(3, 3, 3, 60)
        ));

        Animator.add(new ParallelAnimation(
            Animator.moveCameraTargetTo(10, 0, 0, 120),
            Animator.moveCameraTo(60, 50, 0, 120)
        ));

        Animator.add(Animator.moveCameraTo(50, 0, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 5, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, -5, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 5, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, -5, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, 0, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, 5, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, -5, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, 5, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, -5, 60));

        Animator.add(Animator.moveCameraTargetTo(0, 0, 0, 60));

        Animator.start();

    }
}
