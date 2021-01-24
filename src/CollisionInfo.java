/**
 * The class defines a Block object.
 *
 * @author 323535419
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;

    /**
     * Constructor function.
     *
     * @param point The collision point
     * @param object  The object involved in the collision
     */
    public CollisionInfo(Point point, Collidable object) {
        this.point = point;
        this.object = object;
    }

    /**
     * The function returns the collision point.
     *
     * @return Collision point
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * The function returns the object involved in the collision.
     *
     * @return Collision object
     */
    public Collidable collisionObject() {
        return this.object;
    }
}