import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int dimension;
    private WeightedQuickUnionUF wqu;
    private int topVirtualSite;
    private int bottomVirtualSite;
    private int openSitesCount = 0;
    private int[] opened;
    private int totalNumberOfSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int dimension) {

        totalNumberOfSites = dimension * dimension;

        if (dimension <= 0) {
          throw new IllegalArgumentException("Argument must be a positive number");
        }
        this.dimension = dimension;

        opened = new int[dimension * dimension];

        for (int i = 0; i < opened.length; i++) {
            opened[i] = 0;
            //initially all sites are closed hence 0, when opened turn to 1
        }

        //the +2 is for the top and bottom virtual sites
        wqu = new WeightedQuickUnionUF(dimension * dimension + 2);


        topVirtualSite = dimension * dimension; //second from last element = top virtual site
        bottomVirtualSite = dimension * dimension + 1; //last element = bottom virtual site

        for (int i = 0; i < dimension; i++) {
            wqu.union(topVirtualSite, i); //connect all the top row sites to the top virtual site
        }

        int lastElement = dimension * dimension - 1;
        for (int i = lastElement; i > (lastElement - dimension); i--) {
            wqu.union(bottomVirtualSite, i); //connect all the bottom row sites to the second virtual site
        }

    }

    //my method
    private int connected(int p) {
        return wqu.find(p);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

      if (row > dimension || col > dimension) {
        throw new IllegalArgumentException("Argument not within the valid range");
      }

        row = row - 1;
        col = col - 1;

        // int currentSite = row * col + col;
        int currentSite = (row * dimension) + col;

        int siteToLeft = (row * dimension) + (col - 1);
        int siteToRight = row * (dimension) + (col + 1);

        int siteUp = (row - 1) * dimension + (col);
        int siteDown = (row + 1) * dimension + (col);


        if (opened[currentSite] != 1) {

            opened[currentSite] = 1;
            openSitesCount++;

            if (col != 0) {
                if (wqu.find(currentSite) != wqu.find(siteToLeft) && opened[siteToLeft] == 1) {
                    wqu.union(currentSite, siteToLeft);
                }
            }

            if (col != dimension - 1) {
                if (wqu.find(currentSite) != wqu.find(siteToRight) && opened[siteToRight] == 1) {
                    wqu.union(currentSite, siteToRight);
                }
            }

            if (row != 0) {
                if (wqu.find(currentSite) != wqu.find(siteUp) && opened[siteUp] == 1) {
                    wqu.union(currentSite, siteUp);
                }
            }

            if (row != dimension - 1) {
                if (wqu.find(currentSite) != wqu.find(siteDown) && opened[siteDown] == 1) {
                    wqu.union(currentSite, siteDown);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
      if (row > dimension || col > dimension) {
        throw new IllegalArgumentException("Argument not within the valid range");
      }

      if (this.isFull(row,col)) {
          return true;
      }
      row = row - 1;
      col = col - 1;

      return opened[(row * dimension) + col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
      if (row > dimension || col > dimension) {
        throw new IllegalArgumentException("Argument not within the valid range");
      }
      if (!this.isOpen(row, col)) { // can't be full if it is not open
           return false;
      }
      row = row - 1;
      col = col - 1;

      int currentSite = (row * dimension) + col;


      boolean openTopSite = false;
      for (int i = 0; i < dimension; i++) { // if all top sites are closed no site can be full

        if (opened[i] == 1) {
            openTopSite = true;
        }

      }
      if (!openTopSite) {
          return false;
      }

      return (connected(currentSite) == connected(topVirtualSite));
      }

    // returns the number of open sites
    public int numberOfOpenSites() {
      return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
      return connected(topVirtualSite) == connected(bottomVirtualSite);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 4;
        Percolation perc = new Percolation(n);


        perc.open(1,1);
        perc.open(1,2);
        perc.open(2,2);
        perc.open(2,3);
        perc.open(3,3);
//        perc.open(3,4);
        perc.open(4,4);

        System.out.println(perc.isFull(4,4));

//        for ( int i = 0; i < n*n + 2; i++) {
//            System.out.println(perc.connected(i));
//        }


//        perc.open(1,1);
//        System.out.println(perc.isOpen(1,1));
//        perc.open(2,2);
//        System.out.println(perc.isOpen(2,2));
//        perc.open(2,1);
//        perc.open(2,3);
//        perc.open(2,4);
//        perc.open(3,4);
//        System.out.println(perc.isOpen(3,4));
//        perc.open(4,4);

//        int numberOfTimes =0;
//        while (!perc.percolates()) {
//            perc.open(StdRandom.uniformInt(1, perc.dimension + 1), StdRandom.uniformInt(1, perc.dimension + 1));
//            numberOfTimes++;
//        }
//        System.out.println( "Total opened " + perc.numberOfOpenSites());
//        System.out.println( "Number of times opened " + numberOfTimes);

//        System.out.println("_______________________________");
//        for (int i = 0; i < perc.opened.length; i++) {
//          System.out.println(perc.opened[i]);
//        }
//        System.out.println(perc.percolates());
//        System.out.println(perc.numberOfOpenSites());


    }
}
