package simulator.physicsbodies;

import simulator.colliders.Collider;
import util.Functions;
import util.Vector;

public class Rigidbody implements Physicsbody {
    private final Collider collider;
    private double mass;
    private Vector velocity;

    public Rigidbody(Collider collider, double mass) {
        this.collider = collider;
        this.mass = mass;

        this.velocity = new Vector();
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void applyForce(Vector force) {
        velocity.add(force);
    }

    @Override
    public void applyForce(double x, double y, double z) {
        velocity.add(x, y, z);
    }

    public void applyVelocity() {
        collider.getPos().add(velocity);
    }

    @Override
    public void update(Physicsbody[] bodies) {
        applyVelocity();
        updateCollision(bodies);

    }

    @Override
    public void updatePos() {
        collider.getPos().add(velocity);
    }
    
    @Override
    public void updateCollision(Physicsbody[] bodies) {
        for (Physicsbody other : bodies) {
            if (collider.collidesWith(other.getCollider())) {
                Vector relativeVelocity = Functions.subtractVectors(velocity, other.getVelocity());
                Vector normal = Functions.subtractVectors((collider.getPos()), other.getCollider().getPos()).normalize();

                if (collider == other) {
                    continue;
                }
    
                double relativeVelocityAlongNormal = Functions.getDotProduct(normal, relativeVelocity);
    
                double restitution = 0.9; // Coefficient of restitution (elasticity of the collision)
                double impulseMagnitude = -(1 + restitution) * relativeVelocityAlongNormal / (1 / mass + 1 / other.getMass());
    
                Vector impulse = normal.scale(impulseMagnitude * 0.98);
    
                applyForce(impulse.scale(1 / mass));
                other.applyForce(impulse.scale(-1 / other.getMass()));
            }
        }
    }

}
