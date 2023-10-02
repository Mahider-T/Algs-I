package week1.HelloGoodbye;

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Only two command line arguments are allowed.");
        }else {
            System.out.println(args[0]);
        }

    }
}
