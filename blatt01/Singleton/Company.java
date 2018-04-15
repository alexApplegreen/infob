public class Company {

    private String name;
    private double stockprice;
    private Ticker ticker;

    // Constructor
    public Company(String name, double stockprice) {
        this.name = name;
        this.stockprice = stockprice;
        this.ticker = Ticker.getInstance();
    }

    /**
     * @brief changes stockprice variable and calls ticker.print()
     * @param d new stockprice
     */
    public void changeStockPrice(double d) {
        this.stockprice = d;
        ticker.print(this.name + " " + this.stockprice);
    }

    /**
     * @brief Method to call when company is deleted
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ticker.print(this.name + " " + "is insolvent");
    }
}