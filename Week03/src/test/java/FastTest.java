import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class FastTest extends BaseTest {

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
        Thread.sleep(1000);
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
        Thread.sleep(10000);
        assertTrue(checkLists(tempList, expectedList));
    }

    @org.junit.Test
    public void testGrid5x5() throws Exception {
        List<Set<Point>> tempList = getFastTempList(GRID_5_X_5);
        List<Set<Point>> expectedList = new ArrayList<>();
        assertTrue(checkLists(tempList, expectedList));
        Thread.sleep(10000);
    }
}