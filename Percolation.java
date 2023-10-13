import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int dimension;
    WeightedQuickUnionUF wqu;
    int topVirtualSite;
    int bottomVirtualSite;

    int[] opened;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int dimension) {
        this.dimension = dimension;

        opened = new int[dimension * dimension];
        for ( int i = 0; i < opened.length; i++ ) {
            opened[i] = 0; //initially all sites are closed hence 0, when opened turn to 1
        }

        wqu = new WeightedQuickUnionUF(dimension * dimension + 2); //the +2 is for the top and bottom virtual sites

        topVirtualSite = dimension* dimension; //second from last element = top virtual site
        bottomVirtualSite = dimension * dimension + 1; //last element = bottom virtual site

        for (int i = 0; i < dimension; i++) {
            wqu.union(topVirtualSite, i); //connect all the top row sites to the top virtual site
        }

        int lastElement = dimension * dimension - 1;
        for (int i = lastElement; i > (lastElement - 3); i-- ) {
            wqu.union(bottomVirtualSite, i); //connect all the bottom row sites to the second virtual site
        }

    }

    //my method
    public int connected(int p) {
        return wqu.find(p);
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        // int currentSite = row * col + col;
        int currentSite = (row * dimension) + col;

        int siteToLeft = (row * dimension) + (col-1);
        int siteToRight = row * (dimension) + (col + 1);
        
        int siteUp = (row - 1) * dimension + (col);
        int siteDown = (row + 1) * dimension + (col);

        opened[currentSite] = 1;

      if (col != 0){
        if (wqu.find(currentSite) != wqu.find(siteToLeft) && opened[siteToLeft] == 1) {
          wqu.union(currentSite, siteToLeft);
        }
      }
      
      if (col != dimension-1) {
        if (wqu.find(currentSite) != wqu.find(siteToRight) && opened[siteToRight] == 1) {
          wqu.union(currentSite, siteToRight);
        }
      }

      if (row != 0) {
        if (wqu.find(currentSite) != wqu.find(siteUp) && opened[siteUp] == 1) {
          wqu.union(currentSite, siteUp);
        }
      }
      
      if (row != dimension-1) {
        if (wqu.find(currentSite) != wqu.find(siteDown) && opened[siteDown] == 1) {
          wqu.union(currentSite, siteDown);
        }
      }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[(row * col) + col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){return true;}

    // returns the number of open sites
    public int numberOfOpenSites(){ return 1;}

    // does the system percolate?
    public boolean percolates(){ 
      return connected(topVirtualSite) == connected(bottomVirtualSite);
    }

    // test client (optional)
    public static void main(String[] args){
        int n = 3;
        Percolation perc = new Percolation(n);

        for ( int i = 0; i < n*n + 2; i++) {
            System.out.println(perc.connected(i));
        }

        perc.open(0,0);
        perc.open(1,0);
        perc.open(2,0);

        for ( int i = 0; i < n*n + 2; i++) {
            System.out.println(perc.connected(i));
        }
        System.out.println("_______________________________");
        for (int i = 0; i < perc.opened.length; i++) {
          System.out.println(perc.opened[i]);
        }
        System.out.println(perc.percolates());
        
    
    }
}