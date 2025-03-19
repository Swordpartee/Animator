package controller;

import renderer.display.Camera;
import renderer.io.EventCode;
import renderer.io.RenderListener;
import util.Functions;
import util.Vector;

public class CameraController extends Camera {
    public void moveForward(double distance) {
        Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize().scale(-distance);
        getPosition().add(direction);
        getTarget().add(direction);
    }

    public void moveForwardOnPlane(double distance) {
        Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize().scale(-distance).setY(0);
        getPosition().add(direction);
        getTarget().add(direction);
    }

    public void moveLeftOnPlane(double distance) {
        Vector direction = Functions.crossProduct(Functions.subtractVectors(getPosition(), getTarget()).normalize(), new Vector(0, 1, 0)).normalize().scale(distance);
        getPosition().add(direction);
        getTarget().add(direction);
    }

    public void moveUp(double distance) {
        getTarget().moveY(distance);
        getPosition().moveY(distance);
    }
    
    public void rotateRightOnPlane(double angle) {
        Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize();
        direction.setX(direction.getX() * Functions.cos(-angle) - direction.getZ() * Functions.sin(-angle));
        direction.setZ(direction.getX() * Functions.sin(-angle) + direction.getZ() * Functions.cos(-angle));

        getTarget().set(Functions.subtractVectors(getPosition(), direction));
    }

    public void rotateUp(double angle) {
        Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize();
        double distanceFromorigin = Functions.sqrt(Functions.pow(direction.getX(), 2) + Functions.pow(direction.getZ(), 2));
        double newangle = Functions.clamp(Functions.atan2(direction.getY(), distanceFromorigin) - angle, -89, 89);

        direction.setY(Functions.sin(newangle) * distanceFromorigin);
        direction.scaleX(Functions.cos(newangle));
        direction.scaleZ(Functions.cos(newangle));

        getTarget().set(Functions.subtractVectors(getPosition(), direction));
    }
    
    public void move(RenderListener listener) {
        if (listener.isKeyPressed(EventCode.W)) {
            moveForwardOnPlane(0.1);
        }
        if (listener.isKeyPressed(EventCode.S)) {
            moveForwardOnPlane(-0.1);
        }
        if (listener.isKeyPressed(EventCode.A)) {
            moveLeftOnPlane(0.1);
        }
        if (listener.isKeyPressed(EventCode.D)) {
            moveLeftOnPlane(-0.1);
        }

        if (listener.isKeyPressed(EventCode.RIGHT)) {
            rotateRightOnPlane(0.5);
        }
        if (listener.isKeyPressed(EventCode.LEFT)) {
            rotateRightOnPlane(-0.5);
        }
        if (listener.isKeyPressed(EventCode.UP)) {
            rotateUp(0.5);
        }
        if (listener.isKeyPressed(EventCode.DOWN)) {
            rotateUp(-0.5);
        }
        if (listener.isKeyPressed(EventCode.SPACE)) {
            moveUp(0.1);
        }
        if (listener.isKeyPressed(EventCode.SHIFT)) {
            moveUp(-0.1);
        }
    }
}