import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation implementation using Weighted Quick Union Find algorithm.
 *
 * @author Artem Vegera
 */
public class Percolation {

    /**
     * Length of 2-dimensional grid.
     */
    private final int N;

    /**
     * Grid of sites.
     */
    private boolean[][] grid;

    /**
     * Weighted Quick Union Find algorithm implementation.
     */
    private final WeightedQuickUnionUF quickFindUF;

    /**
     * Virtual bottom index.
     */
    private final int virtualBottomIndex;

    /**
     * Virtual top index.
     */
    private final int virtualTopIndex;

    /**
     * Creates N-by-N grid, with all sites blocked
     *
     * @param N grid size
     */
    public Percolation(int N) {
        checkGridSize(N);
        this.N = N;
        this.grid = new boolean[N][N];
        this.quickFindUF = new WeightedQuickUnionUF(N * N + 2);
        this.virtualBottomIndex = 0;
        this.virtualTopIndex = N * N + 1;
    }

    /**
     * This method opens site (row i, column j) if it is not open already
     *
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {
        checkIndexes(i, j);
        grid[i - 1][j - 1] = true;
        unionWithNeighbours(i, j);
    }

    /**
     * Is site (row i, column j) already open?
     *
     * @param i row number
     * @param j column number
     * @return true if site (row i, column j) is open
     */
    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);
        return grid[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     *
     * @param i row number
     * @param j column number
     * @return true if site (row i, column j) is full
     */
    public boolean isFull(int i, int j) {
        checkIndexes(i, j);
        int siteIndex = convertRowAndColumnToNumber(i, j);
        return quickFindUF.connected(siteIndex, virtualTopIndex);
    }

    /**
     * Does the system percolate?
     *
     * @return true if the system percolate
     */
    public boolean percolates() {
        return quickFindUF.connected(virtualBottomIndex, virtualTopIndex);
    }

    private int convertRowAndColumnToNumber(int row, int column) {
        return (row - 1) * N + column;
    }

    private void checkGridSize(int gridSize) {
        if (gridSize <= 0) {
            String message = "Grid of sites size is out of bounds";
            throw new IllegalArgumentException(message);
        }
    }

    private void checkIndexes(int i, int j) {
        if (i <= 0 || i > N) {
            throw new IndexOutOfBoundsException("Row index i is out of bounds");
        }
        if (j <= 0 || j > N) {
            throw new IndexOutOfBoundsException("Column index j is out of bounds");
        }
    }

    private void unionWithNeighbours(int row, int column) {
        int currentSite = convertRowAndColumnToNumber(row, column);

        //check is left site open
        if (column > 1 && isOpen(row, column - 1)) {
            quickFindUF.union(currentSite, currentSite - 1);
        }

        //check is right site open
        if (column < N && isOpen(row, column + 1)) {
            quickFindUF.union(currentSite, currentSite + 1);
        }

        //check is site below open
        if (row > 1) {
            if (isOpen(row - 1, column)) {
                quickFindUF.union(currentSite, currentSite - N);
            }
        } else {
            quickFindUF.union(virtualBottomIndex, currentSite);
        }

        //check is site above open
        if (row < N) {
            if (isOpen(row + 1, column)) {
                quickFindUF.union(currentSite, currentSite + N);
            }
        } else {
            quickFindUF.union(virtualTopIndex, currentSite);
        }
    }
}