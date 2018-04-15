public class TickerTest {

    public static void main(String[] args) {

        Company weyland = new Company("Weyland Yutani", 2000000d);
        Company lego = new Company("lego", 18564d);

        weyland.changeStockPrice(200000d);
        lego.changeStockPrice(185629d);

        try {
            weyland.finalize();
        }
        catch (Throwable e) {
            // Does nothing
            ;
        }

        Company dharma = new Company("Dharma", 193729d);
        dharma.changeStockPrice(1938203d);
        System.out.println();
    }
}