import objects.Rect;
import tools.Animator;

public class App {
    public static Rect rect1;

    public static void main(String[] args) throws Exception {
        rect1 = new Rect(-10, -10, -10, 3, 3, 3);

        Animator.add(rect1.moveTo(-15, -15, -10, 60));
        
        Animator.add(rect1.moveTo(0, 0, 0, 60));

        Animator.add(rect1.scaleTo(3, 3, 6, 60));

        Animator.add(rect1.scaleTo(3, 3, 1, 60));

        Animator.start();
    }
}
