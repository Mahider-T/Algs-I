import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] id;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        id = new int[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j< n; j++) {
                // id[i][j] = (i*n + j) + 1;
                //initially every site is closed hence 0
                //When a site opens, if:
                    //one of the four neighbours is open : it becomes the child of root of the neighbour
                    //else : it becomes its own parent
                id[i][j] = 0;
            }
        }
    }
    
    public void showArray(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(id[i][j]);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){return true;}

    // is the site (row, col) full?
    public boolean isFull(int row, int col){return true;}

    // returns the number of open sites
    public int numberOfOpenSites(){ return 1;}

    // does the system percolate?
    public boolean percolates(){ return true;}

    // test client (optional)
    public static void main(String[] args){
        Percolation pr = new Percolation(4);
        pr.showArray();
    }
}