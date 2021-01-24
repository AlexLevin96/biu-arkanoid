import java.util.ArrayList;
import java.util.List;

/**
 * The class defines a Rectangle object.
 *
 * @author 323535419
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor function.
     *
     * @param upperLeft The rectangle's upper left point
     * @param width The rectangle's width
     * @param height The rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The function calculates the intersection points of the rectangle with a given line.
     *
     * @param line The given line
     * @return A list of points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<Point>();
        Line[] rectLines = new Line[4];
        // Top line
        rectLines[0] = new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY()));
        // Right line
        rectLines[1] = new Line(rectLines[0].end(), new Point(rectLines[0].end().getX(),
                rectLines[0].end().getY() + this.getHeight()));
        // Bottom line
        rectLines[2] = new Line(rectLines[1].end(), new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight()));
        // Left line
        rectLines[3] = new Line(rectLines[2].end(), this.getUpperLeft());
        for (int i = 0; i < 4; i++) {
            if (line.intersectionWith(rectLines[i]) != null) {
                intersections.add(line.intersectionWith(rectLines[i]));
            }
        }
        return intersections;
    }

    /**
     * The function returns the width of the rectangle.
     *
     * @return Width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The function returns the height of the rectangle.
     *
     * @return Height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * The function returns the upper left point of the rectangle.
     *
     * @return Upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}