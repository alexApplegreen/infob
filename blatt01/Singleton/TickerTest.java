public class TickerTest {

    public static void main(String[] args) {

        boolean passed = true;

        Company weyland = new Company("Weyland Yutani", 2000000d);
        Company lego = new Company("lego", 18564d);

        if (weyland.getStockprice() != 2000000d) {
            passed = false;
            System.out.println("Error while building company");
        }

        weyland.changeStockPrice(100000d);
        if (weyland.getStockprice() != 100000d) {
            passed = false;
            System.out.println("Error while setting stockprice");
        }

        lego.changeStockPrice(185629d);

        try {
            weyland.finalize();
        }
        catch (Throwable e) {
            // Does nothing
            ;
        }

        weyland = null;
        System.gc();

        if (weyland != null) {
            passed = false;
            System.out.println("Error while deleting company");
        }

        Company dharma = new Company("Dharma", 193729d);
        dharma.changeStockPrice(1938203d);
        System.out.println();

        if (passed) {
            System.out.println("Alle Tests erfolgreich");
        }
    }
}