import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    double[] percolatedAt;
    // Percolation perc;

    double mean;
    double stddev;

    int T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if ( n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Entries must be positive integer");
        }

        this.T = trials;

        for ( int i = 0; i < trials; i++) {
            percolatedAt = new double[trials];
            Percolation perc = new Percolation(n);
            double numberOfOpenSites = perc.numberOfOpenSites();
            int dimension = perc.dimension;
            double totalNumberOfSites = perc.totalNumberOfSites;

            while(!perc.percolates()) {
                perc.open(StdRandom.uniformInt(0, dimension), StdRandom.uniformInt(0, dimension));

            }
            percolatedAt[i] = numberOfOpenSites/totalNumberOfSites;


        }


    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(percolatedAt);
        return mean; }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(percolatedAt, 0, percolatedAt.length);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((1.96 * stddev)/Math.sqrt(T)); }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((1.96 * stddev)/Math.sqrt(T));
     }

   // test client (see below)
   public static void main(String[] args) {
    if (args.length != 2) {
        throw new IllegalArgumentException("Argument must be greater than or equal to 0");
    }

    int size = Integer.parseInt(args[0]);
    int trialNumber = Integer.parseInt(args[1]);

    if ( size <= 0 || trialNumber <= 0) {
        throw new IllegalArgumentException("Entries must be positive integer");
    }

    PercolationStats perst= new PercolationStats(size, trialNumber);
    System.out.println(perst.mean);
    System.out.println(perst.stddev);
    System.out.println("[" + perst.confidenceLo() + ", " + perst.confidenceHi() + "]");
   }

}
