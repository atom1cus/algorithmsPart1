import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Percolation statistics.
 *
 * @author Artem Vegera
 */
public class PercolationStats {

    /**
     * Grid size.
     */
    private final int N;

    /**
     * count of experiments.
     */
    private final int T;

    /**
     * Open sites count array.
     */
    private double[] openSitesCount;

    /**
     * Performs T independent experiments on an N-by-N grid.
     *
     * @param N grid size
     * @param T count of experiments
     */
    public PercolationStats(int N, int T) {
        checkParameters(N, T);
        this.N = N;
        this.T = T;
        this.openSitesCount = new double[T];
        runExperiments();
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(openSitesCount);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(openSitesCount);
    }

    /**
     * Low endpoint of 95% confidence interval.
     *
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * High endpoint of 95% confidence interval.
     *
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    private void runExperiments() {
        for (int experimentNumber = 0; experimentNumber < T; experimentNumber++) {
            Percolation percolation = new Percolation(N);
            int openSiteCount = 0;
            while (openSiteCount < N || !percolation.percolates()) {
                int row = StdRandom.uniform(1, N + 1);
                int column = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(row, column)) {
                    percolation.open(row, column);
                    openSiteCount++;
                }
            }
            openSitesCount[experimentNumber] = (double) openSiteCount / (N * N);
        }
    }

    private void checkParameters(int gridSize, int countOfExperiments) {
        if (gridSize <= 0) {
            String message = "Grid size should be positive number.";
            throw new IllegalArgumentException(message);
        }
        if (countOfExperiments <= 0) {
            String message = "Count of experiments should be positive number.";
            throw new IllegalArgumentException(message);
        }
    }

    private static int getArgument(String[] args, int index) {
        return Integer.parseInt(args[index]);
    }

    /**
     * Test client.
     *
     * @param args array of args should have two arguments:
     *             1) N - grid size;
     *             2) T - count of experiments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            String message = "This method should have two arguments: "
                    + "1) N - grid size; 2) T - count of experiments";
            throw new IllegalArgumentException(message);
        }

        int gridSize = getArgument(args, 0);
        int countOfExperiments = getArgument(args, 1);
        PercolationStats stats = new PercolationStats(gridSize, countOfExperiments);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }
}