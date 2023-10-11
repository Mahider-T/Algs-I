import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int dimension;
    WeightedQuickUnionUF wqu;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int dimension) {
        this.dimension = dimension;

        wqu = new WeightedQuickUnionUF(dimension * dimension + 2); //the +2 is for the above and below virtual sites

        int firstVirtualSite = dimension* dimension;
        int secondVirtualSite = dimension * dimension + 1;

        for (int i = 0; i < dimension; i++) {
            wqu.union(firstVirtualSite, i); //connect all the above row sites to the first virtual site
        }

        int lastElement = dimension * dimension - 1;
        for (int i = lastElement; i > (lastElement - 3); i-- ) {
            wqu.union(secondVirtualSite, i); //connect all the bottom row sites to the second virtual site
        }

    }

    public int connected(int p) {
        return wqu.find(p);
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        
        if (!isOpen(row-1, col-1)) {
            
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){return true;}

    // returns the number of open sites
    public int numberOfOpenSites(){ return 1;}

    // does the system percolate?
    public boolean percolates(){ return true;}

    // test client (optional)
    public static void main(String[] args){
        int n = 3;
        Percolation perc = new Percolation(n);
        for ( int i = 0; i < n*n + 2; i++) {
            System.out.println(perc.connected(i));
        }
        
        
    
    }
}