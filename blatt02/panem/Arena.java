import java.lang.Math;
public class Arena {

    // Durchmesser in Meilen
    private final int diameter = 3;
    private Tribut tributes[];

    //Constructor
    public Arena(Tribut... tributes) {
        if (tributes.length > 0) {
            this.tributes = tributes;
        }
    }

    /**
     * @brief returns in which slice of the arena the tribute is present
     * @param x coord of tribute
     * @param y coord of tribute
     * @return a slice of the arena occupied with given tribute
     */
    public int getArea(Tribut tribute) {
        // if tribute is inside arena:
        if(checkCoordinates(tribute)) {
            double a = Math.abs(tribute.getX());
            double b = Math.abs(tribute.getY());
            int alpha = (int)Math.toDegrees(Math.sin(a/b));
            // instead of giant switch case:
            return (alpha / 30) % 12;
        }
        else {
            // errorcode: outside arena
            return -1;
        }
    }

    /**
     * @brief checks if tribut is inside arena bounds
     * @param tribut
     * @return true, if tribut is inside arena, else false
     */
    public boolean checkCoordinates(Tribut tribut) {
        return getDistance(tribut.getX(), tribut.getY()) < 1.5;
    }

    /**
     * @brief calculates the euclidian distance between coordinates and center of arena
     * @param x
     * @param y
     * @return distance
     */
    public double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
    }
}