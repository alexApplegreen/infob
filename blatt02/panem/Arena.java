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
            if (alpha >= 0 && alpha < 30) {
                return 1;
            }
            else if (alpha >= 30 && alpha < 60) {
                return 2;
            }
            else if (alpha >= 60 && alpha < 90) {
                return 3;
            }
            else if (alpha >= 90 && alpha < 120) {
                return 4;
            }
            else if (alpha >= 120 && alpha < 150) {
                return 5;
            }
            else if (alpha >= 180 && alpha < 210) {
                return 6;
            }
            else if (alpha >= 210 && alpha < 240) {
                return 7;
            }
            else if (alpha >= 240 && alpha < 270) {
                return 8;
            }
            else if (alpha >= 270 && alpha < 300) {
                return 9;
            }
            else if (alpha >= 300 && alpha < 330) {
                return 10;
            }
            else {
                return 11;
            }
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
     * @brief calculates the euclidian distance between x and y
     * @param x
     * @param y
     * @return distance
     */
    public double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
    }
}