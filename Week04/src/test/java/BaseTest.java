/**
 * @author Artem Vegera
 * @since 02.04.2015
 */
public class BaseTest {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;

    public static final Board TRIVIAL_ONE = getBoard(ONE, 0);
    public static final Board TRIVIAL_TWO = getBoard(TWO, 1, 2, 3, 0);
    public static final Board TRIVIAL_THREE = getBoard(THREE, 1, 2, 3, 4, 5, 6, 7, 8, 0);
    public static final Board TRIVIAL_FOUR = getBoard(FOUR, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0);

    protected static Board getBoard(int dimension, int... values) {
        int[][] blocks = new int[dimension][dimension];
        for (int row = 0; row < blocks.length; row++) {
            int[] blocksLine = blocks[row];
            System.arraycopy(values, row * dimension, blocksLine, 0, blocksLine.length);
        }
        return new Board(blocks);
    }
}