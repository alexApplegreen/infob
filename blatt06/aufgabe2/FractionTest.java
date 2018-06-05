package aufgabe2;

public class FractionTest {

    public static void main(String[] args) {

        Fraction a = Fraction.getReference(1, 2);
        Fraction b = Fraction.getReference(2, 4);

        System.out.println(a == b);
    }
}