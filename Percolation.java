import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int dimension;
    WeightedQuickUnionUF wqu;

    int[] opened;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int dimension) {
        this.dimension = dimension;

        opened = new int[dimension * dimension];
        for ( int i = 0; i < opened.length; i++ ) {
            opened[i] = 0; //initially all sites are closed hence 0, when opened turn to 1
        }

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

    //my method
    public int connected(int p) {
        return wqu.find(p);
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        int currentSite = row * col + col;

        int siteToLeft = row * (col-1) + (col-1);
        int siteToRight = row * (col + 1) + (col + 1);
        
        int siteUp = (row - 1) * col + (col);
        int siteDown = (row + 1) * col + (col);

        opened[currentSite] = 1;
        
      if (wqu.find(currentSite) != wqu.find(siteToLeft) && opened[siteToLeft] == 1) {
        wqu.union(currentSite, siteToLeft);
      }

      if (wqu.find(currentSite) != wqu.find(siteToRight) && opened[siteToRight] == 1) {
        wqu.union(currentSite, siteToRight);
      }

      if (wqu.find(currentSite) != wqu.find(siteUp) && opened[siteUp] == 1) {
        wqu.union(currentSite, siteUp);
      }

      if (wqu.find(currentSite) != wqu.find(siteDown) && opened[siteDown] == 1) {
        wqu.union(currentSite, siteDown);
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
        int n = 4;
        Percolation perc = new Percolation(n);
        for ( int i = 0; i < n*n + 2; i++) {
            System.out.println(perc.connected(i));
        }

        perc.open(0,1);
        perc.open(1,0);
        perc.open(2,0);

        perc.connected(6);
        
    
    }
}