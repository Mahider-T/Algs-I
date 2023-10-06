class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Only two command line arguments are allowed.");
        } else {
            System.out.println("Hello " + args[0] + " and " + args[1]);
            System.out.println("Goodbye " + args[1] + " and " + args[0]);
        }

    }
}
