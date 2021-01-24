/**
 * The class defines a velocity object.
 *
 * @author 323535419
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor function.
     *
     * @param dx x progression
     * @param dy y progression
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The function returns the x progression.
     *
     * @return x progression
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The function returns the y progression.
     *
     * @return y progression
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The function calculates and returns the speed.
     *
     * @return y progression
     */
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * The function receives a point, applies the velocity to the point and returns it.
     *
     * @param p The given point
     * @return an updated point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * The function receives an angle and speed and converts them to x and y progression.
     *
     * @param angle The given angle
     * @param speed The given speed
     * @return velocity with updated x and y progression values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
}