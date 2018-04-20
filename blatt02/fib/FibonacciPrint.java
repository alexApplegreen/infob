public class FibonacciPrint {

    public static void main(String[] args) {

        if (args[0] == null) {
            System.err.println("Usage: java FibonacciPrint [iteration]");
        }
        Fibonacci f = new Fibonacci();
        System.out.println("| n |  f(n)  |");
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(f.next());
        }
    }
}
