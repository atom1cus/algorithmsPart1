public class PercolationStatsTest {

    @org.junit.Test
    public void testPercolationStats() throws Exception {
        PercolationStats percolationStats = new PercolationStats(400, 100);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }

    @org.junit.Test
    public void testPercolationStatsOne() throws Exception {
        PercolationStats percolationStats = new PercolationStats(1, 1);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}