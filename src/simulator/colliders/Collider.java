package simulator.colliders;

public interface Collider {
    public boolean collliding(Collider other);

    public ColliderType getType();

    public void setPos(float x, float y, float z);

    public void setPos(float[] pos);

    public float[] getPos();
}
