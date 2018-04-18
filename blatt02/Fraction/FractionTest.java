import java.sql.SQLOutput;

public class FractionTest {
    public static void main(String[] args) {

        boolean passed = true;

        Fraction fraction1 = new Fraction(3);
        Fraction fraction2 = new Fraction(1, 8);
        Fraction fraction3 = new Fraction(3, 4);

        if (fraction1 == null) {
            passed = false;
            System.out.println("Instanciation failed");
        }
        if (fraction2 == null) {
            passed = false;
            System.out.println("Instanciation failed");
        }

        Fraction a = fraction1.multiply(4);
        if (!a.toString().equals("12/1")) {
            passed = false;
            System.out.println("Multiplication with factor failed");
        }

        Fraction b = fraction2.divide(fraction3);
        if (!b.toString().equals("1/6")) {
            passed = false;
            System.out.println("Division of two fractions failed");
        }

        Fraction c = fraction2.multiply(fraction3);
        if (!c.toString().equals("3/32")) {
            passed = false;
            System.out.println("Multiplication of two fractions failed");
        }

        Fraction d = fraction2.multiply(fraction3, b);
        if (!d.toString().equals("1/64")) {
            passed = false;
            System.out.println("Multiplication of multiple fractions failed");
        }

        if (passed) {
            System.out.println("Test passed!");
        }
    }

}
