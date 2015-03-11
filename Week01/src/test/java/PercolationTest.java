import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

import static java.io.File.separator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PercolationTest {

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

    @org.junit.Test
    public void testReadingTestFiles() throws Exception {
        File directory = new File(getCurrentPath() + INPUTS);
        Collection<File> files = FileUtils.listFiles(directory, new String[]{TXT}, false);
        for (File inputFile : files) {
            In in = new In(inputFile.getAbsolutePath());
            in.readInt();
        }
    }

    @org.junit.Test
    public void testPercolationGreeting57() throws Exception {
        Percolation percolation = getPercolation(GREETING_57_NO_PERCOLATE);
        checkEmptyOpenSite(percolation, 1, 1);
        checkBlockedSite(percolation, 3, 15);
        checkEmptyOpenSite(percolation, 4, 15);
        checkNoPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationHeart25() throws Exception {
        Percolation percolation = getPercolation(HEART_25_NO_PERCOLATE);
        checkBlockedSite(percolation, 1, 1);
        checkEmptyOpenSite(percolation, 6, 6);
        checkNoPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput1() throws Exception {
        Percolation percolation = getPercolation(INPUT_1_PERCOLATE);
        checkFullOpenSite(percolation, 1, 1);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_1_NO_PERCOLATE);
        checkBlockedSite(percolation2, 1, 1);
        checkNoPercolate(percolation2);
    }


    @org.junit.Test
    public void testPercolationInput2() throws Exception {
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

    @org.junit.Test
    public void testPercolationInput3() throws Exception {
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

    @org.junit.Test
    public void testPercolationInput4() throws Exception {
        Percolation percolation = getPercolation(INPUT_4_PERCOLATE);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput5() throws Exception {
        Percolation percolation = getPercolation(INPUT_5_PERCOLATE);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput6() throws Exception {
        Percolation percolation = getPercolation(INPUT_6_PERCOLATE);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput7() throws Exception {
        Percolation percolation = getPercolation(INPUT_7_PERCOLATE);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput8() throws Exception {
        Percolation percolation = getPercolation(INPUT_8_PERCOLATE);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_8_NO_PERCOLATE);
        checkNoPercolate(percolation2);
    }

    @org.junit.Test
    public void testPercolationInput10() throws Exception {
        Percolation percolation = getPercolation(INPUT_10_PERCOLATE);
        checkPercolate(percolation);

        Percolation percolation2 = getPercolation(INPUT_10_NO_PERCOLATE);
        checkNoPercolate(percolation2);
    }

    @org.junit.Test
    public void testPercolationInput20() throws Exception {
        Percolation percolation = getPercolation(INPUT_20_PERCOLATE);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationInput50() throws Exception {
        Percolation percolation = getPercolation(INPUT_50_PERCOLATE);
        //checkEmptyOpenSite(percolation, 1, 3);
        checkFullOpenSite(percolation, 2, 9);
        checkBlockedSite(percolation, 3, 9);
        checkPercolate(percolation);
    }

    @org.junit.Test
    public void testPercolationExercise() throws Exception {
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
        assertTrue("Site [" + row + "," + column + "] should be open", percolation.isOpen(row, column));
        assertTrue("Site [" + row + "," + column + "] should be full", percolation.isFull(row, column));
    }

    public static void checkEmptyOpenSite(Percolation percolation, int row, int column) {
        assertTrue("Site [" + row + "," + column + "] should be open", percolation.isOpen(row, column));
        assertFalse("Site [" + row + "," + column + "] should not be full", percolation.isFull(row, column));
    }

    public static void checkBlockedSite(Percolation percolation, int row, int column) {
        assertFalse("Site [" + row + "," + column + "] should be blocked", percolation.isOpen(row, column));
        assertFalse("Site [" + row + "," + column + "] should not be full", percolation.isFull(row, column));
    }

    private static void checkPercolate(Percolation percolation) {
        assertTrue("Inputs should percolate!", percolation.percolates());
    }

    private static void checkNoPercolate(Percolation percolation) {
        assertFalse("Inputs should no percolate!", percolation.percolates());
    }

    private static String getFilePath(String fileName) {
        return getCurrentPath() + INPUTS + separator + fileName;
    }

    private static String getCurrentPath() {
        return PercolationTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}