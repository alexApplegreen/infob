public class Fraction {
    private int numerator;  //Zaehler
    private int denominator;  // Nenner

    /**
     * @brief creates a new fraction from an int
     * @param num
     */
    public Fraction(int num) {
        this(num, 1);
    }

    /**
     * @brief creates a new fraction
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Division with 0!");
        }
        int ggt = euclid(numerator, denominator);

        this.numerator = numerator/ggt;
        this.denominator = denominator/ggt;
    }

    /**
     * @brief multiplies the fraction with a given factor
     * @param factor
     * @return
     */
    public Fraction multiply(int factor) {
        int numeratorNew = this.numerator * factor;
        Fraction result = new Fraction (numeratorNew, this.denominator);
        return result;
    }

    /**
     * @brief multiplies the fraction with another given fraction
     * @param factor
     * @return
     */
    public Fraction multiply(Fraction factor) {
        int numeratorNew = this.numerator * factor.numerator;
        int denominatorNew = this.denominator * factor.denominator;
        Fraction result = new Fraction(numeratorNew, denominatorNew);
        return result;
    }

    /**
     * @brief divides the fraction by another given fraction
     * @param divisor
     * @return
     */
    public Fraction divide(Fraction divisor) {
        int numeratorNew = this.numerator * divisor.denominator;
        int denominatorNew = this.denominator * divisor.numerator;
        Fraction result = new Fraction(numeratorNew, denominatorNew);
        return result;
    }

    /**
     * @brief multiplies the fraction with multiple fractions from a given list of Fractions
     * @param factors
     * @return
     */
    public Fraction multiply(Fraction... factors) {
        int numeratorNew = this.numerator;
        int denominatorNew = this.denominator;
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
        int numtemp = this.numerator * addend.denominator;
        int dentemp = this.denominator * addend.denominator;
        int numtemp2 = addend.numerator * this.denominator;
        return new Fraction(numtemp + numtemp2, dentemp);
    }

    public Fraction substract(Fraction subtrahend) {
        return this.add(subtrahend.multiply(-1));
    }

    /**
     * @brief creates a String
     * @return
     */
    public String toString() {
        String result = "";
        result = result + this.numerator + "/" + this.denominator;
        return result;
    }

    /**
     * @brief parses a given string to a Fraction
     * @param string
     * @return
     */
    public static Fraction ParseFraction(String string) {
        if (string.matches("[0-9]*\\/[0-9]*")) {
            String[] parts = string.split("/");
            int numerator = Integer.parseInt(parts[0]);
            int denominator = Integer.parseInt(parts[1]);
            return new Fraction(numerator, denominator);
        }
        else {
            throw new RuntimeException("no Fraction as input");
        }
    }

    private int euclid(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return euclid(b, (a%b));
        }
    }

}
