public class Company {

    private String name;
    private double stockprice;
    private Ticker ticker;

    public Company(String name, double stockprice) {
        this.name = name;
        this.stockprice = stockprice;
        this.ticker = Ticker.getInstance();
    }

    public void changeStockPrice(double d) {
        this.stockprice = d;
        ticker.print(this.name + " " + this.stockprice);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ticker.print(this.name + " " + "is insolvent");
    }
}