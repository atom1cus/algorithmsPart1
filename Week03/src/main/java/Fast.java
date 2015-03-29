import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of fast algorithm for searching of collinear points.
 */
public class Fast {

    /**
     * Stupid API from Coursera task.
     *
     * @param args first argument should contain input file name
     */
    public static void main(String[] args) {
        new Fast().runFastAlgorithm(args[0]);
    }

    private List<Set<Point>> runFastAlgorithm(String fileName) {
        Point[] points = readInputFile(fileName);
        if (checkMinLength(points)) {
            return null;
        }

        setInitialScale();
        List<Set<Point>> list = new ArrayList<>();
        Point[] tempPoints = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length - 1; i++) {
            drawPoint(points[i]);
            Arrays.sort(tempPoints, points[i].SLOPE_ORDER);
            //System.out.println("points[" + i + "] = " + points[i]);

            int samePointsCount = 1;
            int length = tempPoints.length;
            double previousSlope = points[i].slopeTo(tempPoints[0]);
            for (int j = 0; j < length; j++) {
                double nextSlope = points[i].slopeTo(tempPoints[j]);

                if (previousSlope == nextSlope) {
                    samePointsCount++;
                } else {
                    checkSamePoints(points[i], list, tempPoints, samePointsCount, j);
                    samePointsCount = 1;
                }
                previousSlope = nextSlope;
                //System.out.println(tempPoints[j] + " -> "
                //+ points[i].slopeTo(tempPoints[j]));
            }
            checkSamePoints(points[i], list, tempPoints, samePointsCount, length);
        }
        return list;
    }

    private void checkSamePoints(Point point, List<Set<Point>> list, Point[] points,
                                 int countOfSamePoints, int j) {
        if (countOfSamePoints >= 3) {
            addPointsToList(point, list, points, countOfSamePoints, j);
        }
    }

    private void addPointsToList(Point point, List<Set<Point>> list, Point[] points,
                                 int countOfSamePoints, int j) {
        Set<Point> collinearPoints = new HashSet<>();
        collinearPoints.add(point);
        //System.out.println("j = " + j);
        //System.out.println("countOfSamePoints = " + countOfSamePoints);

        int fromIndex = j - countOfSamePoints;
        collinearPoints.addAll(Arrays.asList(points).subList(fromIndex, j));
        //System.out.println("collinearPoints = " + collinearPoints);
        if (isUniquePoints(list, collinearPoints)) {
            printResult(collinearPoints);
            list.add(collinearPoints);
        }
    }

    private void printResult(Set<Point> points) {
        Point[] pointsArray = new Point[points.size()];
        points.toArray(pointsArray);
        Arrays.sort(pointsArray);

        for (int i = 0; i < pointsArray.length - 1; i++) {
            StdOut.print(pointsArray[i] + " -> ");
        }
        StdOut.println(pointsArray[pointsArray.length - 1]);
        pointsArray[0].drawTo(pointsArray[pointsArray.length - 1]);
    }

    private boolean isUniquePoints(List<Set<Point>> list, Set<Point> points) {
        for (Set<Point> pointSet : list) {
            int countOfSamePoint = 0;
            for (Point collinearPoint : points) {
                if (pointSet.contains(collinearPoint)) {
                    countOfSamePoint++;
                }
            }
            if (countOfSamePoint > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMinLength(Point[] points) {
        return points.length < 4;
    }

    private Point[] readInputFile(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
        Point[] points = new Point[N];
        int currentIndex = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            points[currentIndex++] = new Point(x, y);
        }
        return points;
    }

    private void setInitialScale() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        // make the points a bit larger
        StdDraw.setPenRadius(0.01);
    }

    private void drawPoint(Point point) {
        point.draw();
    }
}