import static java.io.File.separator;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.StdDraw;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Artem Vegera
 * @since 28.03.2015
 */
public class BaseTest {

    private static final String INPUTS = "collinear";
    static final String INPUT_4 = "input4.txt";
    static final String INPUT_8 = "input8.txt";
    static final String INPUT_10 = "input10.txt";
    static final String INPUT_20 = "input20.txt";
    static final String INPUT_40 = "input40.txt";
    static final String INPUT_200 = "input200.txt";
    static final String GRID_4_X_4 = "grid4x4.txt";
    static final String GRID_5_X_5 = "grid5x5.txt";
    static final String VERTICAL_5 = "vertical5.txt";
    static final String HORIZONTAL_5 = "horizontal5.txt";

    @BeforeEach
    public void before() {
        System.out.println("================================================");
    }

    @AfterEach
    public void after() {
        StdDraw.show(3000);
        StdDraw.clear();
    }

    List<Set<Point>> getTempList(String fileName) {
        System.out.println(fileName);
        List<Set<Point>> tempList = null;
        try {
            Method method = Brute.class.getDeclaredMethod("runBruteForceAlgorithm", String.class);
            method.setAccessible(true);
            tempList = (List<Set<Point>>) method.invoke(new Brute(), getFilePath(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(tempList);
//        while (true) {
//            Thread.sleep(10000);
//        }
        return tempList;
    }


    List<Set<Point>> getFastTempList(String fileName) {
        System.out.println(fileName);
        List<Set<Point>> tempList = null;
        try {
            Method method = Fast.class.getDeclaredMethod("runFastAlgorithm", String.class);
            method.setAccessible(true);
            tempList = (List<Set<Point>>) method.invoke(new Fast(), getFilePath(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(tempList);
        return tempList;
    }

    boolean checkLists(List<Set<Point>> tempList, List<Set<Point>> expectedList) {
        //TODO: rewrite using containsAll method
        boolean isCorrectResult = true;
        for (Set<Point> expectedPoints : expectedList) {
            boolean isExists = false;
            for (Set<Point> tempPoints : tempList) {
                if (isSameSets(expectedPoints, tempPoints)) {
                    isExists = true;
                }
            }
            if (!isExists) {
                System.out.println("Expected points = " + expectedPoints);
                isCorrectResult = false;
            }
        }
        return isCorrectResult;
    }

    private boolean isSameSets(Set<Point> firstSet, Set<Point> secondSet) {
        if (firstSet.size() != secondSet.size()) {
            return false;
        }
        int samePointsCount = 0;
        for (Point point : firstSet) {
            if (isSetContainPoint(secondSet, point)) {
                samePointsCount++;
            }
        }
        return (firstSet.size() == samePointsCount);
    }

    private boolean isSetContainPoint(Set<Point> points, Point tempPoint) {
        for (Point point : points) {
            if (tempPoint.compareTo(point) == 0) {
                return true;
            }
        }
        return false;
    }

    Set<Point> getSet(Point... point) {
        return new HashSet<>(asList(point));
    }

    private static String getFilePath(String fileName) {
        return getCurrentPath() + INPUTS + separator + fileName;
    }

    private static String getCurrentPath() {
        return BruteTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}