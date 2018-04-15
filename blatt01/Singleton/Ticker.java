public class Ticker {

    //statischer Zeiger auf einzige erlaubte Instanz
    private static Ticker ticker;

    //Constructor nach Singleton Muster
    private Ticker() {

    }

    /**
     * @brief builds instance using constructor
     * @return Ticker instance, if there is none yet
     */
    public static Ticker getInstance() {
        if(ticker == null) {
            ticker = new Ticker();
        }
        return ticker;
    }

    /**
     * @brief formats text parameter
     * @param text
     */
    public void print(String text) {
        System.out.print("+++" + text);
    }
}