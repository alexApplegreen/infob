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
     * @brief creates a String
     * @return
     */
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
