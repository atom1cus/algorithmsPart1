import java.util.*;

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
//            System.out.println("points[i] = " + points[i]);

            int countOfSameElements = 0;
            double previousSlope = points[i].slopeTo(tempPoints[0]);
            for (int j = 0; j < tempPoints.length; j++) {
                double nextSlope = points[i].slopeTo(tempPoints[j]);

                if (previousSlope == nextSlope) {
                    countOfSameElements++;
                } else if (countOfSameElements == 3) {
                    addPointsToList(points[i], list, tempPoints, countOfSameElements, j);
                    countOfSameElements = 0;
                }

                previousSlope = nextSlope;
//                System.out.println(tempPoints[j] + " -> " + points[i].slopeTo(tempPoints[j]));
            }
            if (countOfSameElements >= 3) {
                addPointsToList(points[i], list, tempPoints, countOfSameElements, tempPoints.length);
            }
        }

        return list;
    }

    private void addPointsToList(Point point, List<Set<Point>> list, Point[] tempPoints, int countOfSameElements, int j) {
        Set<Point> collinearPoints = new HashSet<>();
        collinearPoints.add(point);
//        System.out.println("j = " + j);
//        System.out.println("countOfSameElements = " + countOfSameElements);

        int fromIndex = j - countOfSameElements;
        for (int k = fromIndex; k < j; k++) {
//            double initialSlope = point.slopeTo(tempPoints[k]);
//            List<Point> points = Arrays.asList(tempPoints).subList(fromIndex, j + 1);
//            if (isCollinearPoint(initialSlope, points.toArray(new Point[points.size()]), k - fromIndex)) {
                collinearPoints.add(tempPoints[k]);
//            }
        }
//        System.out.println("collinearPoints = " + collinearPoints);
        if (isUniquePoints(list, collinearPoints)) {
            printResult(collinearPoints);
            list.add(collinearPoints);
        }
        //(2000, 22000) -> (3000, 26000) -> (3500, 28000) -> (4000, 30000) -> (1000, 18000)
        //(28000, 13500) -> (23000, 16000) -> (13000, 21000) -> (3000, 26000)
    }

    private boolean isCollinearPoint(double initialSlope, Point[] points, int pointIndex) {
//        for (int index = 0; index < points.length - 1; index++) {
//            boolean isDifferentIndexes = (index != pointIndex);
//            double nextSlope = points[index].slopeTo(points[index + 1]);
//            if (isDifferentIndexes && initialSlope != nextSlope) {
//                return false;
//            }
//        }
        return true;
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
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
    }

    private void drawPoint(Point point) {
        point.draw();
    }
}