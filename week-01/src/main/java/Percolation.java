import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation implementation using Weighted Quick Union Find algorithm.
 * By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site
 *
 * @author Artem Vegera
 */
public class Percolation {

    /**
     * Length of 2-dimensional grid.
     */
    private final int n;

    /**
     * Grid of sites.
     */
    private final boolean[][] grid;

    /**
     * Weighted Quick Union Find algorithm implementation.
     */
    private final WeightedQuickUnionUF weightedQuickUnion;

    /**
     * Virtual bottom index.
     */
    private final int virtualBottomIndex;

    /**
     * Virtual top index.
     */
    private final int virtualTopIndex;

    /**
     * Creates n-by-n grid, with all sites initially blocked
     *
     * @param n grid size
     */
    public Percolation(int n) {
        checkGridSize(n);
        this.n = n;
        this.grid = new boolean[n][n];
        this.weightedQuickUnion = new WeightedQuickUnionUF(n * n + 2);
        this.virtualBottomIndex = 0;
        this.virtualTopIndex = n * n + 1;
    }

    /**
     * Is site (row, column) already open?
     *
     * @param row    row number
     * @param column column number
     * @return true if site (row, column) is open
     */
    public boolean isOpen(int row, int column) {
        checkIndexes(row, column);
        return grid[row - 1][column - 1];
    }

    /**
     * Is the site (row, column) full?
     *
     * @param row    row number
     * @param column column number
     * @return true if the site (row, column) is full
     */
    public boolean isFull(int row, int column) {
        checkIndexes(row, column);
        //TODO: implement it
        int siteIndex = convertRowAndColumnToNumber(row, column);
        return weightedQuickUnion.connected(siteIndex, virtualTopIndex);
    }

    /**
     * Opens the site (row, column) if it is not open already
     *
     * @param row    row number
     * @param column column number
     */
    public void open(int row, int column) {
        checkIndexes(row, column);
        grid[row - 1][column - 1] = true;
        //TODO: implement it
        unionWithNeighbours(row, column);
    }

    /**
     * Returns the number of open sites
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column]) {
                    numberOfOpenSites++;
                }
            }
        }
        return numberOfOpenSites;
    }

    /**
     * Does the system percolate?
     *
     * @return true if the system percolate
     */
    public boolean percolates() {
        //TODO: implement it
        return weightedQuickUnion.connected(virtualBottomIndex, virtualTopIndex);
    }

    private int convertRowAndColumnToNumber(int row, int column) {
        return (row - 1) * n + column;
    }

    private void checkGridSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size is out of bounds");
        }
    }

    private void checkIndexes(int i, int j) {
        if (i <= 0 || i > n) {
            throw new IndexOutOfBoundsException("Row index is out of bounds");
        }
        if (j <= 0 || j > n) {
            throw new IndexOutOfBoundsException("Column index is out of bounds");
        }
    }

    private void unionWithNeighbours(int row, int column) {
        //TODO: implement it
        int currentSite = convertRowAndColumnToNumber(row, column);

        //check is left site open
        if (column > 1 && isOpen(row, column - 1)) {
            weightedQuickUnion.union(currentSite, currentSite - 1);
        }

        //check is right site open
        if (column < n && isOpen(row, column + 1)) {
            weightedQuickUnion.union(currentSite, currentSite + 1);
        }

        //check is site below open
        if (row > 1) {
            if (isOpen(row - 1, column)) {
                weightedQuickUnion.union(currentSite, currentSite - n);
            }
        } else {
            weightedQuickUnion.union(virtualBottomIndex, currentSite);
        }

        //check is site above open
        if (row < n) {
            if (isOpen(row + 1, column)) {
                weightedQuickUnion.union(currentSite, currentSite + n);
            }
        } else {
            weightedQuickUnion.union(virtualTopIndex, currentSite);
        }
    }
}