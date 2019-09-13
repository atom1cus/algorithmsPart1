import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class PercolationTest {

    private final static String TXT = "txt";

    private static final String INPUTS = "inputs";
    private static final String INPUT_1_PERCOLATE = "input1.txt";
    private static final String INPUT_1_NO_PERCOLATE = "input1-no.txt";
    private static final String INPUT_2_PERCOLATE = "input2.txt";
    private static final String INPUT_2_NO_PERCOLATE = "input2-no.txt";
    private static final String INPUT_3_PERCOLATE = "input3.txt";
    private static final String INPUT_4_PERCOLATE = "input4.txt";
    private static final String INPUT_5_PERCOLATE = "input5.txt";
    private static final String INPUT_6_PERCOLATE = "input6.txt";
    private static final String INPUT_7_PERCOLATE = "input7.txt";
    private static final String INPUT_8_PERCOLATE = "input8.txt";
    private static final String INPUT_8_NO_PERCOLATE = "input8-no.txt";
    private static final String INPUT_10_PERCOLATE = "input10.txt";
    private static final String INPUT_10_NO_PERCOLATE = "input10-no.txt";
    private static final String INPUT_20_PERCOLATE = "input20.txt";
    private static final String INPUT_50_PERCOLATE = "input50.txt";
    private static final String GREETING_57_NO_PERCOLATE = "greeting57.txt";
    private static final String HEART_25_NO_PERCOLATE = "heart25.txt";

    @Test
    void testReadingTestFiles() {
        File directory = new File(getCurrentPath() + INPUTS);
        Collection<File> files = FileUtils.listFiles(directory, new String[]{TXT}, false);
        for (File inputFile : files) {
            In in = new In(inputFile.getAbsolutePath());
            in.readInt();
        }
    }

    @Test
    void testPercolationGreeting57() {
        Percolation percolation = getPercolation(GREETING_57_NO_PERCOLATE);
        checkEmptyOpenSite(percolation, 1, 1);
        checkBlockedSite(percolation, 3, 15);
        checkEmptyOpenSite(percolation, 4, 15);
        checkNoPercolate(percolation);
    }

    @Test
    void testPercolationHeart25() {
        Percolation percolation = getPercolation(HEART_25_NO_PERCOLATE);
        checkBlockedSite(percolation, 1, 1);
        checkEmptyOpenSite(percolation, 6, 6);
        checkNoPercolate(percolation);
    }

    @Test
    void testPercolationInput1() {
        Percolation percolation = getPercolation(INPUT_1_PERCOLATE);
        checkFullOpenSite(percolation, 1, 1);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_1_NO_PERCOLATE);
        checkBlockedSite(percolation2, 1, 1);
        checkNoPercolate(percolation2);
    }


    @Test
    void testPercolationInput2() {
        Percolation percolation = getPercolation(INPUT_2_PERCOLATE);
        checkFullOpenSite(percolation, 1, 1);
        checkFullOpenSite(percolation, 1, 2);
        checkFullOpenSite(percolation, 2, 2);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_2_NO_PERCOLATE);
        checkEmptyOpenSite(percolation2, 1, 1);
        checkFullOpenSite(percolation2, 2, 2);
        checkNoPercolate(percolation2);
    }

    @Test
    void testPercolationInput3() {
        Percolation percolation = getPercolation(INPUT_3_PERCOLATE);
        checkFullOpenSite(percolation, 1, 1);
        checkFullOpenSite(percolation, 2, 1);
        checkFullOpenSite(percolation, 3, 1);

        checkBlockedSite(percolation, 1, 2);
        checkBlockedSite(percolation, 2, 2);
        checkBlockedSite(percolation, 3, 2);

        checkFullOpenSite(percolation, 1, 3);
        checkFullOpenSite(percolation, 2, 3);
        checkFullOpenSite(percolation, 3, 3);

        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput4() {
        Percolation percolation = getPercolation(INPUT_4_PERCOLATE);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput5() {
        Percolation percolation = getPercolation(INPUT_5_PERCOLATE);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput6() {
        Percolation percolation = getPercolation(INPUT_6_PERCOLATE);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput7() {
        Percolation percolation = getPercolation(INPUT_7_PERCOLATE);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput8() {
        Percolation percolation = getPercolation(INPUT_8_PERCOLATE);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_8_NO_PERCOLATE);
        checkNoPercolate(percolation2);
    }

    @Test
    void testPercolationInput10() {
        Percolation percolation = getPercolation(INPUT_10_PERCOLATE);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_10_NO_PERCOLATE);
        checkNoPercolate(percolation2);
    }

    @Test
    void testPercolationInput20() {
        Percolation percolation = getPercolation(INPUT_20_PERCOLATE);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationInput50() {
        Percolation percolation = getPercolation(INPUT_50_PERCOLATE);
        //checkEmptyOpenSite(percolation, 1, 3);
        checkFullOpenSite(percolation, 2, 9);
        checkBlockedSite(percolation, 3, 9);
        checkPercolate(percolation);
    }

    @Test
    void testPercolationExercise() {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(0, 1);
        quickFindUF.union(4, 6);
        quickFindUF.union(4, 0);
        quickFindUF.union(0, 9);
        quickFindUF.union(3, 0);
        quickFindUF.union(6, 7);

        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(1, 3);
        weightedQuickUnionUF.union(0, 5);
        weightedQuickUnionUF.union(9, 8);
        weightedQuickUnionUF.union(2, 1);
        weightedQuickUnionUF.union(0, 9);
        weightedQuickUnionUF.union(3, 6);
        weightedQuickUnionUF.union(2, 4);
        weightedQuickUnionUF.union(0, 4);
        weightedQuickUnionUF.union(5, 7);
    }

    private static Percolation getPercolation(String fileName) {
        In in = new In(getFilePath(fileName));
        int N = in.readInt();
        Percolation percolation = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolation.open(i, j);
        }
        return percolation;
    }

    private static void checkFullOpenSite(Percolation percolation, int row, int column) {
        assertTrue(percolation.isOpen(row, column), "Site [" + row + "," + column + "] should be open");
        assertTrue(percolation.isFull(row, column), "Site [" + row + "," + column + "] should be full");
    }

    private static void checkEmptyOpenSite(Percolation percolation, int row, int column) {
        assertTrue(percolation.isOpen(row, column), "Site [" + row + "," + column + "] should be open");
        assertFalse(percolation.isFull(row, column), "Site [" + row + "," + column + "] should not be full");
    }

    private static void checkBlockedSite(Percolation percolation, int row, int column) {
        assertFalse(percolation.isOpen(row, column), "Site [" + row + "," + column + "] should be blocked");
        assertFalse(percolation.isFull(row, column), "Site [" + row + "," + column + "] should not be full");
    }

    private static void checkPercolate(Percolation percolation) {
        assertTrue(percolation.percolates(), "Inputs should percolate!");
    }

    private static void checkNoPercolate(Percolation percolation) {
        assertFalse(percolation.percolates(), "Inputs should no percolate!");
    }

    private static String getFilePath(String fileName) {
        return getCurrentPath() + INPUTS + separator + fileName;
    }

    private static String getCurrentPath() {
        return PercolationTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}