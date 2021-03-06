public class Calculator {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Usage: [Fraction] operator [Fraction]");
        }

        char[] operator = args[1].toCharArray();

        try {
            Fraction a = Fraction.parseFraction(args[0]);
            Fraction b = Fraction.parseFraction(args[2]);
            switch ((int) operator[0]) {
                case 42:
                    System.out.println(a.multiply(b));
                    break;
                case 43:
                    System.out.println(a.add(b));
                    break;
                case 45:
                    System.out.println(a.substract(b));
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
