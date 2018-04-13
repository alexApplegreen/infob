
public class Fraction {

	private int numerator;  //Zähler
	private int denominator;  // Nenner
	
	public Fraction(int num) {
		this(num, 1);
	}
	
	public Fraction(int numerator, int denominator) {
		int ggt = euclid(numerator, denominator);
		
		this.numerator = numerator/ggt;
		this.denominator = denominator/ggt;
	}
	
	public Fraction multiply(int factor) {
		int numeratorNew = this.numerator * factor;
		Fraction result = new Fraction (numeratorNew, this.denominator);
		return result;
	}
	
	public Fraction multiply(Fraction factor) {
		int numeratorNew = this.numerator * factor.numerator;
		int denominatorNew = this.denominator * factor.denominator;
		Fraction result = new Fraction(numeratorNew, denominatorNew);
		return result;
	}
	
	public Fraction divide(Fraction divisor) {
		int numeratorNew = this.numerator * divisor.denominator;
		int denominatorNew = this.denominator * divisor.numerator;
		Fraction result = new Fraction(numeratorNew, denominatorNew);
		return result;
	}
	
	public Fraction multiply(Fraction... factors) {
		Fraction result = new Fraction(this.numerator, this.denominator);
		for(int i = 0; i < factors.length; i++) {
			result.numerator *= factors[i].numerator;
			result.denominator *= factors[i].denominator;
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		result = result + this.numerator + "/" + this.denominator;
		return result;
	}
	
	private int euclid(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return euclid(b, (a%b));
		}
	}
	
}
