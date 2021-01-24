/**
 * Collidable interface.
 *
 * @author 323535419
 */
public interface Collidable {
    /**
     * The function returns the collision rectangle of the object.
     *
     * @return Collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * The function changes the velocity according to a given collision point.
     *
     * @param hitter The object that hit the block
     * @param collisionPoint  The given collision point
     * @param currentVelocity The given velocity
     * @return The new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
