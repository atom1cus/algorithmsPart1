import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Brute {

    /**
     * Stupid API from Coursera task.
     *
     * @param args first argument should contain input file name
     */
    public static void main(String[] args) {
        runBruteForceAlgorithm(args[0]);
    }

    private static List<Set<Point>> runBruteForceAlgorithm(String fileName) {
        Point[] points = readInputFile(fileName);
        if (checkMinLength(points)) {
            return null;
        }

        setInitialScale();
        List<Set<Point>> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            drawPoint(points[i]);
            for (int j = 1; j < points.length; j++) {
                for (int k = 2; k < points.length; k++) {
                    for (int l = 3; l < points.length; l++) {
                        checkPoints(list, points, i, j, k, l);
                    }
                }
            }
        }
        return list;
    }

    private static void setInitialScale() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }

    private static void drawPoint(Point point) {
        point.draw();
    }

    private static void checkPoints(List<Set<Point>> list, Point[] points,
                                    int i, int j, int k, int l) {
        if (isCollinear(points, i, j, k, l)) {
            Set<Point> pointSet = getCollinearPoints(points, i, j, k, l);
            if (isFourPoints(pointSet) && isUniquePoints(list, pointSet)) {
                list.add(pointSet);
                printResult(points, i, j, k , l);
            }
        }
    }

    private static boolean isFourPoints(Set<Point> collinearPoints) {
        return collinearPoints.size() == 4;
    }

    private static boolean isUniquePoints(List<Set<Point>> list, Set<Point> points) {
        //TO-DO: is it necessary?
        for (Set<Point> pointSet : list) {
            int countOfSamePoint = 0;
            for (Point collinearPoint : points) {
                if (pointSet.contains(collinearPoint)) {
                    countOfSamePoint++;
                }
            }
            //TO-DO: improve for 8 points in line
            if (countOfSamePoint > 1) {
                return false;
            }
        }
        return true;
    }

    private static Set<Point> getCollinearPoints(Point[] points,
                                                     int i, int j, int k, int l) {
        Set<Point> collinearPoints = new HashSet<>();
        collinearPoints.add(points[i]);
        collinearPoints.add(points[j]);
        collinearPoints.add(points[k]);
        collinearPoints.add(points[l]);
        return collinearPoints;
    }

    private static boolean checkMinLength(Point[] points) {
        return points.length < 4;
    }

    private static void printResult(Point[] points, int i, int j, int k, int l) {
        Point[] tempArray = {points[i], points[j], points[k], points[l]};
        Arrays.sort(tempArray);
        StdOut.println(tempArray[0] + " -> " + tempArray[1] + " -> "
                + tempArray[2] + " -> " + tempArray[3]);
        tempArray[0].drawTo(tempArray[3]);
    }

    private static boolean isCollinear(Point[] points, int i, int j, int k, int l) {
        Point firstPoint = points[i];
        double slope1 = firstPoint.slopeTo(points[j]);
        double slope2 = firstPoint.slopeTo(points[k]);
        double slope3 = firstPoint.slopeTo(points[l]);
        return (slope1 == slope2 && slope2 == slope3);
    }

    private static Point[] readInputFile(String filePath) {
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
}