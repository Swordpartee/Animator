package controller;

import renderer.display.Camera;
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
        Vector direction = new Vector(0, 1, 0).scale(distance);
        getPosition().add(direction);
        getTarget().add(direction);
    }

    public void rotate(double angle) {
        // Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize();
        // Vector newDirection = new Vector(
        //         direction.getX() * Functions.cos(angle) - direction.getZ() * Functions.sin(angle),
        //         direction.getY(),
        //         direction.getX() * Functions.sin(angle) + direction.getZ() * Functions.cos(angle));
        // getTarget().setX(getPosition().getX() + newDirection.getX());
        // getTarget().setY(getPosition().getY() + newDirection.getY());
        // getTarget().setZ(getPosition().getZ() + newDirection.getZ());
    }
    
    public void rotateOnPlane(double angle) {
        Vector direction = Functions.subtractVectors(getPosition(), getTarget()).normalize();
        Vector newDirection = new Vector(
                direction.getX() * Functions.cos(angle) - direction.getZ() * Functions.sin(angle),
                direction.getY(),
                direction.getX() * Functions.sin(angle) + direction.getZ() * Functions.cos(angle));
        getTarget().set(Functions.subtractVectors(getPosition(), newDirection));
    }
}