import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class FastTest extends BaseTest {

    //TODO: Test 7 (stdraw): Check that each point is drawn exactly once
//    Test 7 (stdraw): Check that each point is drawn exactly once
//    *  equidistant.txt
//    -  student   solution has 14 non-null entries
//    -  reference solution has 15 non-null entries
//    -  1 missing entry in student solution: (9000, 6000)
//            *  input40.txt
//    -  student   solution has 39 non-null entries
//    -  reference solution has 40 non-null entries
//    -  1 missing entry in student solution: (19000, 2000)
//            *  input48.txt
//    -  student   solution has 47 non-null entries
//    -  reference solution has 48 non-null entries
//    -  1 missing entry in student solution: (26000, 6000)


//    TODO: Timing Fast
//    *-----------------------------------------------------------
//    Running 13 total tests.
//
//    Maximum time allowed per test = 10 seconds
//
//
//    Test 1a-1g: Find collinear points among N random distinct points
//
//    slopeTo()
//    N    time     slopeTo()   compare()  + 2*compare()        compareTo()
//            -----------------------------------------------------------------------------------------------
//            => passed   128   0.15       16383       87791         191965                    0
//            => passed   256   0.12       65535      411205         887945                    0
//            => passed   512   0.25      262143     1876067        4014277                    0
//            => passed  1024   0.45     1048575     8467940       17984455                    0
//
//    lg ratio(slopeTo() + 2*compare()) = lg (17984455 / 4014277) = 2.16
//            => passed
//
//    ==> 5/5 tests passed
//
//
//    Test 2a-2e: Find collinear points among the 4N points on an N x 4 grid
//
//                                                                      slopeTo()
//    N    time     slopeTo()   compare()  + 2*compare()        compareTo()
//            -----------------------------------------------------------------------------------------------
//            => passed   128   9.24      262143     1108555        2479253                32153
//
//    Total: 0/13 tests passed: Could not complete tests in allotted time, which results in a reported score of 0.


    @org.junit.Test
    public void testInput8() throws Exception {
        List<Set<Point>> tempList = getFastTempList(INPUT_8);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(10000, 0), new Point(0, 10000), new Point(3000, 7000), new Point(7000, 3000)));
        expectedList.add(getSet(new Point(3000, 4000), new Point(20000, 21000), new Point(14000, 15000), new Point(6000, 7000)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testInput10() throws Exception {
        List<Set<Point>> tempList = getFastTempList(INPUT_10);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(4000, 30000), new Point(3500, 28000), new Point(3000, 26000), new Point(2000, 22000), new Point(1000, 18000)));
        expectedList.add(getSet(new Point(3000, 26000), new Point(13000, 21000), new Point(23000, 16000), new Point(28000, 13500)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testInput20() throws Exception {
        List<Set<Point>> tempList = getFastTempList(INPUT_20);
        List<Set<Point>> expectedList = new ArrayList<>();
//        (4096, 20992) -> (5120, 20992) -> (6144, 20992) -> (7168, 20992) -> (8128, 20992)
//        (4096, 20992) -> (4096, 22016) -> (4096, 23040) -> (4096, 24064) -> (4096, 25088)
//        (4096, 25088) -> (5120, 25088) -> (7168, 25088) -> (8192, 25088)
//        (8192, 25088) -> (8192, 26112) -> (8192, 27136) -> (8192, 28160) -> (8192, 29184)
//        (4160, 29184) -> (5120, 29184) -> (6144, 29184) -> (7168, 29184) -> (8192, 29184)
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testInput40() throws Exception {
        List<Set<Point>> tempList = getFastTempList(INPUT_40);
        List<Set<Point>> expectedList = new ArrayList<>();
//        (1000, 17000) -> (13000, 17000) -> (17000, 17000) -> (29000, 17000)
//        (1000, 17000) -> (1000, 27000) -> (1000, 28000) -> (1000, 31000)
//        (2000, 24000) -> (9000, 24000) -> (14000, 24000) -> (25000, 24000)
//        (2000, 29000) -> (4000, 29000) -> (22000, 29000) -> (28000, 29000)
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testInput200() throws Exception {
        List<Set<Point>> tempList = getFastTempList(INPUT_200);
//        (14600, 2500) -> (26400, 14300) -> (27000, 14900) -> (28600, 16500)
//        (10700, 9000) -> (11100, 9400) -> (19900, 18200) -> (22400, 20700)
//        (24100, 20700) -> (21100, 21700) -> (9400, 25600) -> (1900, 28100)
//        (10400, 6900) -> (10400, 7200) -> (10400, 17700) -> (10400, 25800)
        assertTrue(checkLists(tempList, new ArrayList<Set<Point>>()));
    }

    @org.junit.Test
    public void testVertical5() throws Exception {
        List<Set<Point>> tempList = getFastTempList(VERTICAL_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(14407, 10367), new Point(14407, 17188), new Point(14407, 17831), new Point(14407, 19953)));
        expectedList.add(getSet(new Point(15976, 3370), new Point(15976, 4589), new Point(15976, 8933), new Point(15976, 9945)));
        expectedList.add(getSet(new Point(2088, 6070), new Point(2088, 7091), new Point(2088, 11500), new Point(2088, 16387)));
        expectedList.add(getSet(new Point(5757, 3426), new Point(5757, 13581), new Point(5757, 16647), new Point(5757, 20856)));
        expectedList.add(getSet(new Point(8421, 1829), new Point(8421, 11344), new Point(8421, 15144), new Point(8421, 18715)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testHorizontal5() throws Exception {
        List<Set<Point>> tempList = getFastTempList(HORIZONTAL_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(2682, 14118), new Point(5067, 14118), new Point(7453, 14118), new Point(7821, 14118)));
        expectedList.add(getSet(new Point(4750, 4652), new Point(5766, 4652), new Point(9972, 4652), new Point(16307, 4652)));
        expectedList.add(getSet(new Point(8934, 7996), new Point(10411, 7996), new Point(13291, 7996), new Point(20547, 7996)));
        expectedList.add(getSet(new Point(1888, 7657), new Point(7599, 7657), new Point(12772, 7657), new Point(13832, 7657)));
        expectedList.add(getSet(new Point(10375, 12711), new Point(14226, 12711), new Point(18177, 12711), new Point(20385, 12711)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testGrid4x4() throws Exception {
        List<Set<Point>> tempList = getFastTempList(GRID_4_X_4);
        List<Set<Point>> expectedList = new ArrayList<>();
//        (0, 0) -> (5000, 0) -> (10000, 0) -> (15000, 0) -> (20000, 0)
//        (0, 0) -> (5000, 5000) -> (10000, 10000) -> (15000, 15000) -> (20000, 20000)
//        (0, 0) -> (0, 5000) -> (0, 10000) -> (0, 15000) -> (0, 20000)
//        (0, 5000) -> (5000, 5000) -> (10000, 5000) -> (15000, 5000) -> (20000, 5000)
//        (0, 5000) -> (5000, 10000) -> (10000, 15000) -> (15000, 20000)
//        (0, 10000) -> (5000, 10000) -> (10000, 10000) -> (15000, 10000) -> (20000, 10000)
//        (15000, 0) -> (10000, 5000) -> (5000, 10000) -> (0, 15000)
//        (0, 15000) -> (5000, 15000) -> (10000, 15000) -> (15000, 15000) -> (20000, 15000)
//        (20000, 0) -> (15000, 5000) -> (10000, 10000) -> (5000, 15000) -> (0, 20000)
//        (0, 20000) -> (5000, 20000) -> (10000, 20000) -> (15000, 20000) -> (20000, 20000)
//        (5000, 0) -> (10000, 5000) -> (15000, 10000) -> (20000, 15000)
//        (5000, 0) -> (5000, 5000) -> (5000, 10000) -> (5000, 15000) -> (5000, 20000)
//        (20000, 5000) -> (15000, 10000) -> (10000, 15000) -> (5000, 20000)
//        (10000, 0) -> (10000, 5000) -> (10000, 10000) -> (10000, 15000) -> (10000, 20000)
//        (15000, 0) -> (15000, 5000) -> (15000, 10000) -> (15000, 15000) -> (15000, 20000)
//        (20000, 0) -> (20000, 5000) -> (20000, 10000) -> (20000, 15000) -> (20000, 20000)
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testGrid5x5() throws Exception {
        List<Set<Point>> tempList = getFastTempList(GRID_5_X_5);
        List<Set<Point>> expectedList = new ArrayList<>();
//        (0, 0) -> (5000, 0) -> (10000, 0) -> (15000, 0) -> (20000, 0) -> (25000, 0)
//        (0, 0) -> (5000, 5000) -> (10000, 10000) -> (15000, 15000) -> (20000, 20000) -> (25000, 25000)
//        (0, 0) -> (0, 5000) -> (0, 10000) -> (0, 15000) -> (0, 20000) -> (0, 25000)
//        (0, 5000) -> (5000, 5000) -> (10000, 5000) -> (15000, 5000) -> (20000, 5000) -> (25000, 5000)
//        (0, 5000) -> (5000, 10000) -> (10000, 15000) -> (15000, 20000) -> (20000, 25000)
//        (0, 10000) -> (5000, 10000) -> (10000, 10000) -> (15000, 10000) -> (20000, 10000) -> (25000, 10000)
//        (0, 10000) -> (5000, 15000) -> (10000, 20000) -> (15000, 25000)
//        (15000, 0) -> (10000, 5000) -> (5000, 10000) -> (0, 15000)
//        (0, 15000) -> (5000, 15000) -> (10000, 15000) -> (15000, 15000) -> (20000, 15000) -> (25000, 15000)
//        (20000, 0) -> (15000, 5000) -> (10000, 10000) -> (5000, 15000) -> (0, 20000)
//        (0, 20000) -> (5000, 20000) -> (10000, 20000) -> (15000, 20000) -> (20000, 20000) -> (25000, 20000)
//        (25000, 0) -> (20000, 5000) -> (15000, 10000) -> (10000, 15000) -> (5000, 20000) -> (0, 25000)
//        (0, 25000) -> (5000, 25000) -> (10000, 25000) -> (15000, 25000) -> (20000, 25000) -> (25000, 25000)
//        (5000, 0) -> (10000, 5000) -> (15000, 10000) -> (20000, 15000) -> (25000, 20000)
//        (5000, 0) -> (5000, 5000) -> (5000, 10000) -> (5000, 15000) -> (5000, 20000) -> (5000, 25000)
//        (25000, 5000) -> (20000, 10000) -> (15000, 15000) -> (10000, 20000) -> (5000, 25000)
//        (10000, 0) -> (15000, 5000) -> (20000, 10000) -> (25000, 15000)
//        (10000, 0) -> (10000, 5000) -> (10000, 10000) -> (10000, 15000) -> (10000, 20000) -> (10000, 25000)
//        (25000, 10000) -> (20000, 15000) -> (15000, 20000) -> (10000, 25000)
//        (15000, 0) -> (15000, 5000) -> (15000, 10000) -> (15000, 15000) -> (15000, 20000) -> (15000, 25000)
//        (20000, 0) -> (20000, 5000) -> (20000, 10000) -> (20000, 15000) -> (20000, 20000) -> (20000, 25000)
//        (25000, 0) -> (25000, 5000) -> (25000, 10000) -> (25000, 15000) -> (25000, 20000) -> (25000, 25000)
        assertTrue(checkLists(tempList, expectedList));
    }
}