import java.util.List;

/**
 * The class defines a Line segment object.
 *
 * @author 323535419
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor function using 2 points.
     *
     * @param start The line's starting point
     * @param end   The line's ending point
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructor function using 2 x values and 2 y values.
     *
     * @param x1 The line's starting point's x value
     * @param y1 The line's starting point's y value
     * @param x2 The line's end point's x value
     * @param y2 The line's end point's y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The function calculates and returns the length of the line segment.
     *
     * @return length of the line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The function calculates and returns the middle point of the line segment.
     *
     * @return middle point of the line segment
     */
    public Point middle() {
        double middleX = this.start.getX() / 2 + this.end.getX() / 2;
        double middleY = this.start.getY() / 2 + this.end.getY() / 2;
        return new Point(middleX, middleY);
    }

    /**
     * The function returns the starting point of the line segment.
     *
     * @return starting point of the line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * The function returns the end point of the line segment.
     *
     * @return end point of the line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function checks if two lines intersect.
     *
     * @param other The other line to check
     * @return true if the lines intersect, otherwise returns false
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * The function checks if two lines intersect, if they do it calculates their intersection point and returns it.
     *
     * @param other The other line to check
     * @return point of intersection if they intersect, otherwise null
     */
    public Point intersectionWith(Line other) {
        //The function uses the line formula: y = ax + b
        Point p;
        double startX = this.start.getX(), endX = this.end.getX(),
                startXOther = other.start.getX(), endXOther = other.end.getX(),
                startY = this.start.getY(), endY = this.end.getY(),
                startYOther = other.start.getY(), endYOther = other.end.getY();
        // Check if both lines are vertical
        if (startX == endX && startXOther == endXOther) {
            return null;
        }
        // Check if the first line is vertical
        if (startX == endX) {
            double a2 = (endYOther - startYOther) / (endXOther - startXOther);
            double b2 = startYOther - (a2 * startXOther);
            double y = (a2 * startX) + b2;
            double preciseX = precision(startX, 3);
            double preciseY = precision(y, 3);
            Point tempPoint = this.ifContainsPoint(other, preciseX, preciseY);
            if (tempPoint != null) {
                return tempPoint;
            }
            p = this.ifContainsPoint(other, startX, y);
            return p;
        }
        // Check if the second line is vertical
        if (startXOther == endXOther) {
            double a1 = (endY - startY) / (endX - startX);
            double b1 = startY - (a1 * startX);
            double y = (a1 * startXOther) + b1;
            double preciseX = precision(startXOther, 3);
            double preciseY = precision(y, 3);
            Point tempPoint = this.ifContainsPoint(other, preciseX, preciseY);
            if (tempPoint != null) {
                return tempPoint;
            }
            p = this.ifContainsPoint(other, startXOther, y);
            return p;
        }
        // Calculate the incline of the lines
        double a1 = (endY - startY) / (endX - startX);
        double a2 = (endYOther - startYOther) / (endXOther - startXOther);
        if (a1 == a2) {
            return null;
        }
        // Gets the b values
        double b1 = startY - (a1 * startX);
        double b2 = startYOther - (a2 * startXOther);
        // Gets the intersection x value
        double x = (b2 - b1) / (a1 - a2);
        // Gets the intersection y value
        double y = (a1 * x) + b1;
        double p1 = precision(x, 3);
        double p2 = precision(y, 3);
        // Check if both lines contain the point
        p = this.ifContainsPoint(other, x, y);
        Point tempPoint = this.ifContainsPoint(other, p1, p2);
        if (tempPoint != null) {
            return tempPoint;
        }
        return p;
    }

    /**
     * The function recieves and number and returns a rounded version of it.
     *
     * @param num The given number
     * @param precision How precise it should be
     * @return The updated number
     */
    private double precision(double num, int precision) {
        double per10 = Math.pow(10, precision);
        return Math.round(num * per10) / per10;
    }

    /**
     * The function checks if a line contains a given point.
     *
     * @param other The other line
     * @param x The x value of a given point
     * @param y The y value of a given point
     * @return null the line doesn't contain the point, otherwise returns the point
     */
    public Point ifContainsPoint(Line other, double x, double y) {
        double startX = this.start.getX(), endX = this.end.getX(),
                startXOther = other.start.getX(), endXOther = other.end.getX(),
                startY = this.start.getY(), endY = this.end.getY(),
                startYOther = other.start.getY(), endYOther = other.end.getY();
        if (x <= Math.max(startX, endX) && x >= Math.min(startX, endX)
                && y <= Math.max(startY, endY) && y >= Math.min(startY, endY)
                && x <= Math.max(startXOther, endXOther) && x >= Math.min(startXOther, endXOther)
                && y <= Math.max(startYOther, endYOther) && y >= Math.min(startYOther, endYOther)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * The function checks if two lines are equal.
     *
     * @param other The other line to check
     * @return true if the lines are equal, otherwise returns false
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }

    /**
     * The function checks if a given point is on the line.
     *
     * @param point The given point
     * @return true if the line contains the point, otherwise return false
     */
    public boolean pointOnLine(Point point) {
        double aX, aY, bX, bY, cX, cY;
        aX = this.start.getX();
        aY = this.start.getY();
        bX = point.getX();
        bY = point.getY();
        cX = this.end.getX();
        cY = this.end.getY();
        // if AC is vertical
        if (aX == cX) {
            return bX == cX;
        }
        // if AC is horizontal
        if (aY == cY) {
            return bY == cY;
        }
        // match the gradients
        return (aX - cX) * (aY - cY) == (cX - bX) * (cY - bY);
    }

    /**
     * The function checks if the line intersects a given rectangle, if it does return the closest intersection point.
     *
     * @param rect The given rectangle
     * @return null if there are no intersection points, otherwise returns the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point startingPoint = this.start;
        Point minDistance = null;
        List<Point> pointList;
        pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        double tempDistance = Double.POSITIVE_INFINITY;
        //searches for the shortest distance.
        for (int i = 0; i < pointList.size(); i++) {
            Point pointToCheck = pointList.get(i);
            if (startingPoint.distance(pointToCheck) <= tempDistance) {
                minDistance = pointToCheck;
                tempDistance = startingPoint.distance(pointToCheck);
            }
        }
        return minDistance;
    }
}