import java.util.Comparator;

/**
 * An immutable data type for points in the plane.
 *
 * @author Artem Vegera
 */
public class Point implements Comparable<Point> {

    /**
     * Compare points by slope.
     */
    public final Comparator<Point> SLOPE_ORDER = null;

    /**
     * Coordinate x.
     */
    private final int x;

    /**
     * Coordinate y.
     */
    private final int y;

    /**
     * Creates the point (x, y).
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Plots this point to standard drawing.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draw line between this point and that point to standard drawing.
     *
     * @param that that point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Slope between this point and that point
     *
     * @param that that point
     * @return slope value
     */
    public double slopeTo(Point that) {
        //TODO: implement it
        return 0.0;
    }

    /**
     * Is this point lexicographically smaller than that one?
     * Comparing y-coordinates and breaking ties by x-coordinates.
     *
     * @param that point
     * @return comparison result
     */
    public int compareTo(Point that) {
        //TODO: implement it
        return this.compareTo(that);
    }

    /**
     * Return string representation of this point.
     *
     * @return string representation of this point.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}