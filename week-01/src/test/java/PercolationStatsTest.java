import org.junit.jupiter.api.Test;

class PercolationStatsTest {

    @Test
    void testPercolationStats() {
        PercolationStats percolationStats = new PercolationStats(400, 100);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }

    @Test
    void testPercolationStatsOne() {
        PercolationStats percolationStats = new PercolationStats(1, 1);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}