import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

class BoardTest extends BaseTest {

    @Test
    void testDimension() {
        assertEquals(1, TRIVIAL_ONE.dimension());
        assertEquals(2, TRIVIAL_TWO.dimension());
        assertEquals(3, TRIVIAL_THREE.dimension());
        assertEquals(4, TRIVIAL_FOUR.dimension());
    }

    @Test
    void testIsGoal() {
        assertTrue(TRIVIAL_ONE.isGoal());
        assertTrue(TRIVIAL_TWO.isGoal());
        assertTrue(TRIVIAL_THREE.isGoal());
        assertTrue(TRIVIAL_FOUR.isGoal());
    }

    @Test
    void testIsNotGoal() {
        assertFalse(getBoard(TWO, 1, 3, 2, 0).isGoal());
        assertFalse(getBoard(THREE, 1, 2, 3, 4, 7, 8, 0, 5, 6).isGoal());
        assertFalse(getBoard(THREE, 2, 1, 3, 4, 5, 6, 7, 8, 0).isGoal());
        assertFalse(getBoard(FOUR, 7, 1, 6, 15, 4, 5, 0, 13, 14, 9, 8, 11, 3, 12, 10, 2).isGoal());
    }

    @Test
    void testEqual() {
        assertEquals(TRIVIAL_ONE, TRIVIAL_ONE);
        assertEquals(TRIVIAL_TWO, TRIVIAL_TWO);
        assertEquals(TRIVIAL_THREE, TRIVIAL_THREE);
        assertEquals(TRIVIAL_FOUR, TRIVIAL_FOUR);

        assertNotEquals(getBoard(TWO, 1, 3, 2, 0), getBoard(TWO, 2, 3, 1, 0));
        assertNotEquals(getBoard(THREE, 2, 1, 3, 4, 5, 6, 7, 8, 0), getBoard(THREE, 4, 2, 3, 1, 8, 6, 0, 7, 5));
        assertNotEquals(getBoard(FOUR, 2, 1, 5, 10, 4, 6, 0, 13, 14, 9, 11, 8, 3, 12, 15, 7), TRIVIAL_FOUR);
    }

    @Test
    void testHamming() {
        assertEquals(0, TRIVIAL_ONE.hamming());

        assertEquals(0, TRIVIAL_TWO.hamming());
        assertEquals(2, getBoard(TWO, 1, 3, 2, 0).hamming());
        assertEquals(3, getBoard(TWO, 2, 3, 1, 0).hamming());

        assertEquals(0, TRIVIAL_THREE.hamming());
        assertEquals(2, getBoard(THREE, 2, 1, 3, 4, 5, 6, 7, 8, 0).hamming());
        assertEquals(3, getBoard(THREE, 1, 2, 3, 4, 5, 7, 6, 0, 8).hamming());
        assertEquals(5, getBoard(THREE, 4, 2, 3, 1, 8, 6, 0, 7, 5).hamming());
        assertEquals(5, getBoard(THREE, 8, 1, 3, 4, 0, 2, 7, 6, 5).hamming());
        assertEquals(8, getBoard(THREE, 2, 3, 4, 5, 6, 7, 8, 0, 1).hamming());

        assertEquals(0, TRIVIAL_FOUR.hamming());
        assertEquals(14, getBoard(FOUR, 7, 1, 6, 15, 4, 5, 0, 13, 14, 9, 11, 8, 3, 12, 10, 2).hamming());
    }

    @Test
    void testManhattan() {
        assertEquals(0, TRIVIAL_ONE.manhattan());

        assertEquals(0, TRIVIAL_TWO.manhattan());
        assertEquals(4, getBoard(TWO, 1, 3, 2, 0).manhattan());
        assertEquals(4, getBoard(TWO, 2, 3, 1, 0).manhattan());

        assertEquals(0, TRIVIAL_THREE.manhattan());
        assertEquals(4, getBoard(THREE, 0, 1, 3, 4, 2, 5, 7, 8, 6).manhattan());
        assertEquals(10, getBoard(THREE, 8, 1, 3, 4, 0, 2, 7, 6, 5).manhattan());
        assertEquals(20, getBoard(THREE, 0, 8, 7, 6, 5, 4, 3, 2, 1).manhattan());

        assertEquals(0, TRIVIAL_FOUR.manhattan());
        assertEquals(39, getBoard(FOUR, 7, 1, 6, 15, 4, 5, 0, 13, 14, 9, 11, 8, 3, 12, 10, 2).manhattan());
    }

//    @Test
//    void testTwin() {
//        assertEquals(TRIVIAL_ONE, TRIVIAL_ONE.twin());
//        System.out.println("getBoard(TWO, 1, 2, 0, 3) = " + getBoard(TWO, 1, 2, 0, 3));
//        System.out.println("getBoard(TWO, 2, 1, 0, 3).twin() = " + getBoard(TWO, 2, 1, 0, 3).twin());
//        assertEquals(getBoard(TWO, 1, 2, 0, 3), getBoard(TWO, 2, 1, 0, 3).twin());
//        assertEquals(TRIVIAL_TWO, getBoard(TWO, 1, 2, 0, 3).twin());
//        assertEquals(getBoard(TWO, 0, 1, 2, 3), getBoard(TWO, 1, 0, 2, 3).twin());
//        assertEquals(getBoard(TWO, 1, 0, 2, 3), getBoard(TWO, 0, 1, 2, 3).twin());
//
//        assertEquals(getBoard(THREE, 1, 2, 3, 4, 5, 6, 7, 0, 8), TRIVIAL_THREE.twin());
//        assertEquals(TRIVIAL_THREE, getBoard(THREE, 1, 2, 3, 4, 5, 6, 7, 0, 8).twin());
//        assertEquals(getBoard(THREE, 1, 2, 3, 4, 5, 6, 7, 0, 8), getBoard(THREE, 1, 0, 3, 4, 5, 6, 7, 2, 8));
//        assertEquals(getBoard(THREE, 4, 5, 6, 0, 8, 1, 2, 3, 7), getBoard(THREE, 4, 5, 6, 0, 8, 1, 2, 3, 7));
//    }

}