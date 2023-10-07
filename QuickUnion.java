import edu.princeton.cs.algs4.StdOut;

public class QuickUnion{
    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];

        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int findRoot(int p) {
        int root = p;
        while (id[root] != root) {
            root = id[root];
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return (findRoot(p) == findRoot(q));
    }

    public void union(int p, int q) {
        int rootOfP = findRoot(p);
        int rootOfQ = findRoot(q);

        id[rootOfP] = rootOfQ;

    }

    public void printArray(){
        for (int i = 0; i < id.length; i++) {
            if ( i == id.length -1) {
                StdOut.print(id[i] + "\n");    
            } else {
            StdOut.print(id[i] + " ");
            }
        }
    }


    public static void main(String[] args) {

        QuickUnion QU = new QuickUnion(10);
        QU.printArray();

        // QU.union(5, 6);
        // QU.union(0, 5);
        // QU.union(0, 1);
        // QU.union(1,2);
        // QU.union(1,7);
        // QU.union(1,8);
        // QU.union(3,8);
        // QU.union(3,4);
        // QU.union(8,9);

        QU.union(1, 2);
        QU.union(2, 3);

        QU.printArray();
        StdOut.println(QU.connected(1, 3));
    }
}