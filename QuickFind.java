import edu.princeton.cs.algs4.StdOut;

public class QuickFind{
    //check if connected
    //connect two objects
    //initiate the list

    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for ( int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        if (id[p] == id[q]) {
            return true;
        }
        return false;
        
    }

    public void union(int p, int q) {
        for(int i = 0; i < id.length; i++) {
            if (id[i] == id[p]) {
                id[i] = id[q];
            }
        }
    }

    public void printId(){
        for (int i = 0; i < id.length; i++){
            if(i == id.length-1){
                StdOut.print(id[i] + "\n");
            } else{
                StdOut.print(id[i] + " ");
            }
            
        }
    }


    public static void main(String[] args) {

        QuickFind QF = new QuickFind(10);
        
        QF.printId();
        
        StdOut.println("---------------------");
        StdOut.println(QF.connected(1, 2));
        StdOut.println("---------------------");
        
        QF.union(1, 2);
        StdOut.println(QF.connected(1, 2));
        StdOut.println("---------------------");
        QF.printId();

        QF.union(2, 8);
        StdOut.println(QF.connected(1, 8));
        StdOut.println("---------------------");

        QF.printId();




    }
}