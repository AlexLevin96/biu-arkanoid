import biuoop.DrawSurface;

/**
 * The class defines a Ball object.
 *
 * @author 323535419
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;

    /**
     * Constructor function.
     *
     * @param center The ball's center point
     * @param r      The ball's radius
     * @param color  the ball's color
     * @param environment The game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * The function returns the x value of the ball's center point.
     *
     * @return x value of the ball's center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The function returns the y value of the ball's center point.
     *
     * @return y value of the ball's center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The function returns the radius of the ball.
     *
     * @return radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * The function returns the color of the ball.
     *
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function receives a velocity object and sets it to be the ball's velocity.
     *
     * @param vel The given velocity
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * The function receives an x progression and y progression values, creates a new velocity object and sets it to be.
     * the ball's velocity.
     *
     * @param dx x progression
     * @param dy y progression
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * The function returns the velocity of the ball.
     *
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * The function moves the ball using the current velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo closestCollisionInfo = environment.getClosestCollision(trajectory);
        Collidable object = null;
        if (closestCollisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Point closestCollision = closestCollisionInfo.collisionPoint();
            object = closestCollisionInfo.collisionObject();
            setVelocity(object.hit(this, closestCollision, this.v));
            this.center = this.getVelocity().applyToPoint(this.center);
            for (int i = 0; i < this.environment.getCollidables().size(); i++) {
                if (trajectory.pointOnLine(closestCollisionInfo.collisionPoint())) {
                    this.center = new Point(this.center.getX(), this.center.getY() - 20);
                    return;
                }
            }
        }
    }

    /**
     * The function draws the ball on a given surface.
     *
     * @param surface The given surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    /**
     * The function notifies the ball that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * The function adds the ball to the game.
     *
     * @param game The given game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * The function removes the ball from the game.
     *
     * @param g The given game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}