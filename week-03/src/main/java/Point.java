import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;
import java.util.Objects;

/**
 * An immutable data type for points in the plane.
 *
 * @author Artem Vegera
 */
public class Point implements Comparable<Point> {

    /**
     * Positive zero constant.
     */
    private static final double POSITIVE_ZERO = 0.0d;

    /**
     * Compare points by slope.
     */
    private final Comparator<Point> SLOPE_ORDER = new NewComparator();

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
        StdDraw.point(this.x, this.y);
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
        if (that.y == this.y && that.x == this.x) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.y == this.y) {
            return POSITIVE_ZERO;
        } else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Is this point lexicographically smaller than that one?
     * Comparing y-coordinates and breaking ties by x-coordinates.
     *
     * @param that point
     * @return comparison result
     */
    @Override
    public int compareTo(Point that) {
        if (this.y == that.y && this.x == that.x) {
            return 0;
        } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                Objects.equals(SLOPE_ORDER, point.SLOPE_ORDER);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SLOPE_ORDER, x, y);
    }

    /**
     * Return string representation of this point.
     *
     * @return string representation of this point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private class NewComparator implements Comparator<Point> {

        @Override
        public int compare(Point first, Point second) {
            Point invokingPoint = new Point(x, y);
            double firstSlope = invokingPoint.slopeTo(first);
            double secondSlope = invokingPoint.slopeTo(second);
            return Double.compare(firstSlope, secondSlope);
        }
    }

    Comparator<Point> getSlopeOrder() {
        return SLOPE_ORDER;
    }
}