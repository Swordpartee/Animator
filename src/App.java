import animations.CompAnimation;
import drawables.Circle;
import drawables.Rect;
import tools.Animator;

public class App {
    public static Rect rect1;
    public static Circle circle1;

    public static void main(String[] args) throws Exception {
        rect1 = new Rect(0, 0, 100, 100);
        circle1 = new Circle(0, 0, 50);

        Animator.add(circle1.moveTo(400, 300, 120));

        Animator.add(rect1.moveTo(600, 600, 60));
        Animator.add(rect1.moveTo(600, 0, 60));
        Animator.add(rect1.scaleTo(50, 50, 45));

        Animator.add(new CompAnimation(
            rect1.moveTo(400, 300, 120),
            rect1.scaleTo(200, 200, 60)
        ));

        Animator.add(new CompAnimation(
            rect1.moveTo(400, 0, 20),
            circle1.moveTo(400, 0, 20)
        ));
        Animator.add(new CompAnimation(
            rect1.moveTo(800, 300, 20),
            circle1.moveTo(800, 300, 20)
        ));
        Animator.add(new CompAnimation(
            rect1.moveTo(400, 600, 20),
            circle1.moveTo(400, 600, 20)
        ));
        Animator.add(new CompAnimation(
            rect1.moveTo(0, 300, 20),
            circle1.moveTo(0, 300, 20)
        ));
        Animator.add(new CompAnimation(
            rect1.moveTo(400, 0, 20),
            circle1.moveTo(400, 0, 20)
        ));

        Animator.start();
    }
}
