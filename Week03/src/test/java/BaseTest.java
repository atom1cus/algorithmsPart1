import org.junit.After;
import org.junit.Before;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.io.File.separator;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;

/**
 * @author Artem Vegera
 * @since 28.03.2015
 */
public class BaseTest {

    protected static final String INPUTS = "collinear";
    protected static final String INPUT_4 = "input4.txt";
    protected static final String INPUT_8 = "input8.txt";
    protected static final String INPUT_10 = "input10.txt";
    protected static final String INPUT_20 = "input20.txt";
    protected static final String INPUT_40 = "input40.txt";
    protected static final String INPUT_200 = "input200.txt";
    protected static final String GRID_4_X_4 = "grid4x4.txt";
    protected static final String GRID_5_X_5 = "grid5x5.txt";
    protected static final String VERTICAL_5 = "vertical5.txt";
    protected static final String HORIZONTAL_5 = "horizontal5.txt";

    @Before
    public void before() throws Exception {
        System.out.println("================================================");
    }

    @After
    public void after() throws Exception {
        StdDraw.show(3000);
        StdDraw.clear();
    }

    protected List<Set<Point>> getTempList(String fileName) throws InterruptedException {
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


    protected List<Set<Point>> getFastTempList(String fileName) throws InterruptedException {
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

    protected boolean checkLists(List<Set<Point>> tempList, List<Set<Point>> expectedList) {
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

    protected boolean isSameSets(Set<Point> firstSet, Set<Point> secondSet) {
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

    protected boolean isSetContainPoint(Set<Point> points, Point tempPoint) {
        for (Point point : points) {
            if (tempPoint.compareTo(point) == 0) {
                return true;
            }
        }
        return false;
    }

    protected Set<Point> getSet(Point... point) {
        return new HashSet<>(asList(point));
    }

    protected static String getFilePath(String fileName) {
        return getCurrentPath() + INPUTS + separator + fileName;
    }

    protected static String getCurrentPath() {
        return BruteTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}