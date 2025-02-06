import animation.Animator;
import java.awt.Color;
import renderer.objects.Rect;

public class App {
    public static Rect rect1;

    public static void main(String[] args) throws Exception {
        rect1 = new Rect(-10, -10, -10, 3, 3, 3, Color.BLACK);

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

        Animator.start();
    }
}
