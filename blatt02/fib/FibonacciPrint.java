public class FibonacciPrint {

    public static void main(String[] args) {

        if (args[0] == null) {
            System.err.println("Usage: java FibonacciPrint [iteration]");
        }
        int iteration = Integer.parseInt(args[0]);
        Fibonacci f = new Fibonacci();
        System.out.printf("| %2s %2s  %9s  %5s %n", "n", "|", "f(n)", "|");
        System.out.println("+-----+-----------------+");
        for (int i = 0; i <= iteration; i++) {
          System.out.printf("| %2d %2s %15d %s %n", i, "|", f.next(), "|");
        }
    }
}
