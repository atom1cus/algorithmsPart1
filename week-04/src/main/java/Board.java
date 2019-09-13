import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Board class.
 */
public class Board {

    /**
     * Dimension of board.
     */
    private final int N;

    /**
     * Board blocks.
     */
    private final int[][] blocks;

    /**
     * Constructs a board from an N-by-N array of blocks.
     *
     * @param blocks blocks[i][j] = block in row i, column j
     */
    public Board(int[][] blocks) {
        this.N = blocks.length;
        this.blocks = clone2DArray(blocks);
        //System.out.println(this.toString());
    }

    /**
     * Gets board dimension N.
     *
     * @return board dimension
     */
    public int dimension() {
        return blocks.length;
    }

    /**
     * Hamming priority function.
     *
     * @return number of blocks out of place
     */
    public int hamming() {
        int counter = 1;
        int wrongPositionCount = 0;
        for (int row = 0; row < N; row++) {
            int length = getLengthOfLine(row);
            for (int column = 0; column < length; column++) {
                if (isWrongPosition(counter, row, column)) {
                    wrongPositionCount++;
                }
                counter++;
            }
        }
        return wrongPositionCount;
    }

    /**
     * Calculates sum of Manhattan distances between blocks and goal.
     *
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int distances = 0;
        int blockCounter = 1;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (blocks[row][column] != 0
                        && isWrongPosition(blockCounter, row, column)) {
                    distances += getDistance(row, column, blocks[row][column]);
                }
                blockCounter++;
            }
        }
        return distances;
    }

    /**
     * Is this board the goal board?
     *
     * @return true is this board the goal board
     */
    public boolean isGoal() {
        int counter = 1;
        for (int row = 0; row < N; row++) {
            int[] block = blocks[row];
            int length = getLengthOfLine(row);
            for (int column = 0; column < length; column++) {
                if (block[column] != counter) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }

    /**
     * Gets twin-board that is obtained by exchanging
     * two adjacent blocks in the same row.
     *
     * @return board that is obtained by exchanging two adjacent blocks
     */
    public Board twin() {
        //TO-DO: it's wrong work. Reimplement it.
        if (N == 1) {
            return new Board(blocks);
        }
        int[][] twinBlocks = clone2DArray(blocks);
        int zeroBlockIndex = getIndex(1);
        Block block = new Block(zeroBlockIndex);
        if (block.column == N - 1) {
            int tempBlock = twinBlocks[block.row][block.column - 1];
            twinBlocks[block.row][block.column - 1]
                    = twinBlocks[block.row][block.column];
            twinBlocks[block.row][block.column] = tempBlock;
        } else {
            int tempBlock = twinBlocks[block.row][block.column + 1];
            twinBlocks[block.row][block.column + 1]
                    = twinBlocks[block.row][block.column];
            twinBlocks[block.row][block.column] = tempBlock;
        }
        return new Board(twinBlocks);
    }

    /**
     * Does this board equal object?
     *
     * @param object another board
     * @return true if this board equal object
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Board board = (Board) object;
        if (this.N != board.N) {
            return false;
        }

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != board.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(blocks);
        return result;
    }

    /**
     * Gets all neighboring boards.
     *
     * @return iterator for all neighboring boards
     */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int zeroBlockIndex = getIndex(0);
        Block block = new Block(zeroBlockIndex);
        if (block.row > 0) {
            neighbors.add(swapBlocks(block, new Block(block.row - 1, block.column)));
        }
        if (block.row < N - 1) {
            neighbors.add(swapBlocks(block, new Block(block.row + 1, block.column)));
        }
        if (block.column > 0) {
            neighbors.add(swapBlocks(block, new Block(block.row, block.column - 1)));
        }
        if (block.column < N - 1) {
            neighbors.add(swapBlocks(block, new Block(block.row, block.column + 1)));
        }
        return neighbors;
    }

    /**
     * String representation of this board.
     *
     * @return string representation of this board
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(N).append("\n");
        String format = "%" + String.valueOf(N * N).length() + "d";
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N - 1; column++) {
                builder.append(String.format(format, blocks[row][column]));
                builder.append(" ");
            }
            builder.append(String.format(format, blocks[row][N - 1]));
            builder.append("\n");
        }
        return builder.toString();
    }

    private Board swapBlocks(Block block1, Block block2) {
        int[][] clone = clone2DArray(blocks);
        int tempBlock = clone[block1.row][block1.column];
        clone[block1.row][block1.column] = clone[block2.row][block2.column];
        clone[block2.row][block2.column] = tempBlock;
        return new Board(clone);
    }

    private int getIndex(int value) {
        int counter = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == value) {
                    return counter;
                }
                counter++;
            }
        }
        return counter;
    }

    private boolean isWrongPosition(int blockCounter, int row, int column) {
        return blocks[row][column] != blockCounter;
    }

    private int[][] clone2DArray(int[][] source) {
        int length = source.length;
        int[][] clone = new int[length][source[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(source[i], 0, clone[i], 0, source[i].length);
        }
        return clone;
    }

    private int getDistance(int fromRow, int fromColumn, int value) {
        Block to = new Block(value);
        return Math.abs(to.column - fromColumn) + Math.abs(to.row - fromRow);
    }

    private int getLengthOfLine(int i) {
        //don't check last zero-block
        if (i == N - 1) {
            return N - 1;
        } else {
            return N;
        }
    }

    /**
     * Util block class.
     */
    private class Block {

        private int row;

        private int column;

        Block(int value) {
            if (value % N == 0) {
                this.row = value / N - 1;
            } else {
                this.row = value / N;
            }
            if (value % N == 0) {
                this.column = N - 1;
            } else {
                this.column = value % N - 1;
            }
        }

        Block(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}