import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class BruteTest extends BaseTest {

//    TODO: Test 5 (stddraw): Points from a file with 5 or more on any line segment
//    *  input6.txt
//    -  student   solution has 1 non-null entries
//    -  reference solution has 5 non-null entries
//    -  4 missing entries in student solution, including: (14000, 10000) -> (32000, 10000)
//            *  input9.txt
//    -  student   solution has 3 non-null entries
//    -  reference solution has 126 non-null entries
//    -  123 missing entries in student solution, including: (5000, 5000) -> (9000, 9000)
//            *  input10.txt
//    -  student   solution has 2 non-null entries
//    -  reference solution has 6 non-null entries
//    -  4 missing entries in student solution, including: (1000, 18000) -> (4000, 30000)
//            *  input20.txt
//    -  student   solution has 5 non-null entries
//    -  reference solution has 21 non-null entries
//    -  16 missing entries in student solution, including: (5120, 29184) -> (8192, 29184)
//            ==> FAILED


    @Test
    void testInput4() {
        List<Set<Point>> tempList = getTempList(INPUT_4);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(10000, 0), new Point(0, 10000), new Point(3000, 7000), new Point(7000, 3000)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @Test
    void testInput8() {
        List<Set<Point>> tempList = getTempList(INPUT_8);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(10000, 0), new Point(0, 10000), new Point(3000, 7000), new Point(7000, 3000)));
        expectedList.add(getSet(new Point(3000, 4000), new Point(20000, 21000), new Point(14000, 15000), new Point(6000, 7000)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @Test
    void testInput10() throws InterruptedException {
        List<Set<Point>> tempList = getTempList(INPUT_10);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(4000, 30000), new Point(3500, 28000), new Point(3000, 26000), new Point(2000, 22000)));
        expectedList.add(getSet(new Point(3000, 26000), new Point(13000, 21000), new Point(23000, 16000), new Point(28000, 13500)));
        Thread.sleep(10000);
        assertTrue(checkLists(tempList, expectedList));
    }

    @Test
    void testVertical5() {
        List<Set<Point>> tempList = getTempList(VERTICAL_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(14407, 10367), new Point(14407, 17188), new Point(14407, 17831), new Point(14407, 19953)));
        expectedList.add(getSet(new Point(15976, 3370), new Point(15976, 4589), new Point(15976, 8933), new Point(15976, 9945)));
        expectedList.add(getSet(new Point(2088, 6070), new Point(2088, 7091), new Point(2088, 11500), new Point(2088, 16387)));
        expectedList.add(getSet(new Point(5757, 3426), new Point(5757, 13581), new Point(5757, 16647), new Point(5757, 20856)));
        expectedList.add(getSet(new Point(8421, 1829), new Point(8421, 11344), new Point(8421, 15144), new Point(8421, 18715)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @Test
    void testHorizontal5() {
        List<Set<Point>> tempList = getTempList(HORIZONTAL_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        expectedList.add(getSet(new Point(2682, 14118), new Point(5067, 14118), new Point(7453, 14118), new Point(7821, 14118)));
        expectedList.add(getSet(new Point(4750, 4652), new Point(5766, 4652), new Point(9972, 4652), new Point(16307, 4652)));
        expectedList.add(getSet(new Point(8934, 7996), new Point(10411, 7996), new Point(13291, 7996), new Point(20547, 7996)));
        expectedList.add(getSet(new Point(1888, 7657), new Point(7599, 7657), new Point(12772, 7657), new Point(13832, 7657)));
        expectedList.add(getSet(new Point(10375, 12711), new Point(14226, 12711), new Point(18177, 12711), new Point(20385, 12711)));
        assertTrue(checkLists(tempList, expectedList));
    }

    @Test
    void testGrid5x5() {
        List<Set<Point>> tempList = getTempList(GRID_5_X_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        assertTrue(checkLists(tempList, expectedList));
    }
}