import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percolatedAt;
    // Percolation perc;

    private final int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Entries must be positive integer");
        }

        this.t = trials;
        percolatedAt = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            double numberOfOpenSites;
//            int dimension = n;
            double totalNumberOfSites = n * n;

            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
//                System.out.println("Open Sites for trial " + i + perc.numberOfOpenSites());
//                System.out.println("Total number of sites " + perc.totalNumberOfSites);
            }
            numberOfOpenSites = perc.numberOfOpenSites();
//            System.out.println("Open " + numberOfOpenSites);
            percolatedAt[i] = numberOfOpenSites / totalNumberOfSites;


        }


    }

//     sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolatedAt);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolatedAt, 0, this.percolatedAt.length);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(t)); }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(t));
     }

   // test client (see below)
   public static void main(String[] args) {
    if (args.length != 2) {
        throw new IllegalArgumentException("Argument must be greater than or equal to 0");
    }

    int size = Integer.parseInt(args[0]);
    int trialNumber = Integer.parseInt(args[1]);

    if (size <= 0 || trialNumber <= 0) {
        throw new IllegalArgumentException("Entries must be positive integer");
    }

    PercolationStats perst = new PercolationStats(size, trialNumber);
    System.out.println(size + " " + trialNumber);
    System.out.println("Mean " + perst.mean());
    System.out.println("Stddev " + perst.stddev());
    System.out.println("[" + perst.confidenceLo() + ", " + perst.confidenceHi() + "]");
//    for (int i = 0; i < perst.percolatedAt.length; i++) {
//        System.out.println(perst.percolatedAt[i]);
//    }
   }

}
