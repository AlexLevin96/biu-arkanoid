import java.util.ArrayList;
import java.util.List;

/**
 * The class defines a GameEnvironment object.
 *
 * @author 323535419
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor function.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * The function adds a new collidable to the environment.
     *
     * @param c A new collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * The function removes a collidable from the environment.
     *
     * @param c The collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * The function returns a list of all the collidables.
     *
     * @return List of collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * The function recieves a trajectory and checks if it intersects any of the collidables in the environment.
     * If it does return the collision info, otherwise return null.
     *
     * @param trajectory The given trajectory
     * @return Collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable coll, tempColl = null;
        Point point, tempPoint = null;
        double distance = Double.POSITIVE_INFINITY;
        // Gets the closest collision
        for (Collidable c : this.collidables) {
            coll = c;
            point = trajectory.closestIntersectionToStartOfLine(coll.getCollisionRectangle());
            if (point == null) {
                continue;
            } else if (trajectory.start().distance(point) < distance) {
                distance = trajectory.start().distance(point);
                tempPoint = point;
                tempColl = coll;
            }
        }
        if (tempPoint == null) {
            return null;
        }
        return new CollisionInfo(tempPoint, tempColl);
    }
}