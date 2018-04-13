
public class FractionTest {

	public static void main(String[] args) {
		
		Fraction fraction1 = new Fraction(1);
		Fraction fraction2 = new Fraction(32, 42);
		Fraction fraction3 = new Fraction(88, 1453);
		
		Fraction a = fraction1.multiply(3);
		System.out.println(a.toString());
		
		Fraction b = fraction2.divide(fraction3);
		System.out.println(b.toString());
		
		Fraction c = fraction2.multiply(fraction3);
		System.out.println(c.toString());

		
		Fraction d = fraction2.multiply(fraction2, fraction3, a, b);
		System.out.println(d.toString());
	}

}
