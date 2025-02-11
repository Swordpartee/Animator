package simulator;

import simulator.objects.PhysicsObject;
import java.util.ArrayList;
import java.util.List;
import renderer.Factory;

import renderer.display.Camera;
import renderer.display.Renderer;
import simulator.bodies.PhysicsBody;

public class Simulator {
    private final List<PhysicsBody> bodies = new ArrayList<>();

    
    public void register(PhysicsBody object) {
        bodies.add(object);
    }

    public List<PhysicsBody> getObjects() {
        return bodies;
    }

    public void update() {
        bodies.forEach(body -> body.applyVelocity());
        bodies.forEach(body -> body.updateCollision());
    }

}
