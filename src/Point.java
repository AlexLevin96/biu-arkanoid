/**
 * The class defines a point object.
 *
 * @author 323535419
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor function.
     *
     * @param x The point's x value
     * @param y The point's y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function calculates and returns the distance between two points.
     *
     * @param other The other point
     * @return distance between two points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * The function checks if two points are equal.
     *
     * @param other The other point
     * @return true if the points are equal, otherwise returns false
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * The function returns the x value of the point.
     *
     * @return x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function returns the y value of the point.
     *
     * @return y value of the point
     */
    public double getY() {
        return this.y;
    }
}
