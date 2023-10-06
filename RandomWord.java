import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class RandomWord {
    public static void main(String[] args) {

        StdOut.println("Enter the string");
        double i = 1;
        String winner = "";

        while (!StdIn.isEmpty()) {
            String theString = StdIn.readString();
            if (StdRandom.bernoulli(1 / i)) {
                winner = theString;
            }
            i++;
        }
        StdOut.println(winner);
    }
    }

