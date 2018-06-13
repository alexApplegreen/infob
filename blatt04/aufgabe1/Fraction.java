public class Fraction extends Number {

    private float numerator;  //Zaehler
    private float denominator;  // Nenner

    /**
     * @brief creates a new fraction from an int
     * @param num
     */
    public Fraction(float num) {
        this(num, 1);
    }

    /**
     * @brief creates a new fraction
     * @param numerator
     * @param denominator
     */
    public Fraction(float numerator, float denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Division with 0!");
        }
        float ggt = euclid(numerator, denominator);

        this.numerator = numerator/ggt;
        this.denominator = denominator/ggt;
    }
    // implemenatation of abstract number methods
    public int intValue() {
        return (int)(this.numerator / this.denominator);
    }

    public long longValue() {
        return (long)(this.numerator / this.denominator);
    }

    public float floatValue() {
        return (float)(this.numerator / this.denominator);
    }

    public double doubleValue() {
        return (double)(this.numerator / this.denominator);
    }

    /**
     * @brief multiplies the fraction with a given factor
     * @param factor
     * @return
     */
    public Fraction multiply(float factor) {
        float numeratorNew = this.numerator * factor;
        Fraction result = new Fraction (numeratorNew, this.denominator);
        return result;
    }

    /**
     * @brief multiplies the fraction with another given fraction
     * @param factor
     * @return
     */
    public Fraction multiply(Fraction factor) {
        float numeratorNew = this.numerator * factor.numerator;
        float denominatorNew = this.denominator * factor.denominator;
        Fraction result = new Fraction(numeratorNew, denominatorNew);
        return result;
    }

    /**
     * @brief divides the fraction by another given fraction
     * @param divisor
     * @return
     */
    public Fraction divide(Fraction divisor) {
        float numeratorNew = this.numerator * divisor.denominator;
        float denominatorNew = this.denominator * divisor.numerator;
        Fraction result = new Fraction(numeratorNew, denominatorNew);
        return result;
    }

    /**
     * @brief multiplies the fraction with multiple fractions from a given list of Fractions
     * @param factors
     * @return
     */
    public Fraction multiply(Fraction... factors) {
        float numeratorNew = this.numerator;
        float denominatorNew = this.denominator;
        for(int i = 0; i < factors.length; i++) {
            numeratorNew *= factors[i].numerator;
            denominatorNew *= factors[i].denominator;
        }
        Fraction result = new Fraction(numeratorNew, denominatorNew);
        return result;
    }

    /**
     * @brief adds 2 Fractions
     * @param addend
     * @return sum of 2 addends
     */
    public Fraction add(Fraction addend) {
        float numtemp = this.numerator * addend.denominator;
        float dentemp = this.denominator * addend.denominator;
        float numtemp2 = addend.numerator * this.denominator;
        return new Fraction(numtemp + numtemp2, dentemp);
    }

    /**
     * @brief subtracts 2 Fractions
     * @param subtrahend
     * @return new Fraction
     */
    public Fraction substract(Fraction subtrahend) {
        return this.add(subtrahend.multiply(-1));
    }

    /**
     * @brief creates a String
     * @return
     */
    public String toString() {
        String result = "";
        if (this.denominator == 1.0) {
            result += this.numerator;
        }
        else {
            result = result + this.numerator + "/" + this.denominator;
        }
        return result;
    }

    /**
     * @brief parses a given string to a Fraction
     * @param string
     * @return
     */
    public static Fraction parseFraction(String string) {
        String floatRegex = "[-]?[0-9]*\\.?[0-9]+";
        if (string.matches("[0-9]*\\/[0-9]*") ||
            string.matches("[-]?[0-9]*\\.?[0-9]+\\/[-]?[0-9]*\\.?[0-9]+")) {
            String[] parts = string.split("/");
            float numerator = Float.parseFloat(parts[0]);
            float denominator = Float.parseFloat(parts[1]);
            return new Fraction(numerator, denominator);
        }
        if (string.matches("[0-9]*")) {
            return new Fraction(Float.parseFloat(string));
        }
        if (string.matches(floatRegex)) {
            return new Fraction(Float.parseFloat(string));
        }
        else {
            throw new IllegalArgumentException("no Fraction as input");
        }
    }

    /**
     * @brief calculates euclidian divisor
     * @param a
     * @param b
     * @return int
     */
    private float euclid(float a, float b) {
        if (b == 0) {
            return a;
        } else {
            return euclid(b, (a%b));
        }
    }
}
