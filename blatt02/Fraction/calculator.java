public class Calculator {

    public static void main(String[] args) {

        Fraction a = Fraction.parse(args[0]);
        Fraction b = Fraction.parse(args[2]);
        try {
            switch ((int) args[1]) {
                case 42:
                    System.out.println(a.multiply(b));
                    break;
                case 43:
                    System.out.println(a.add(b));
                    break;
                case 47:
                    System.out.println(a.divide(b));
                    break;
            }
        }
        catch (RuntimeException e) {
            System.err.println("Division by 0!");
        }
    }
}