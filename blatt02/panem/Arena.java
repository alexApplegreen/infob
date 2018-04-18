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
        if(checkCoordinates(tribute)) {
            System.out.println("doing stuff");
            return 1;
        }
        else {
            return -1;
        }
    }

    /**
     * @brief checks if tribut is inside arena bounds
     * @param tribut
     * @return true, if tribut is inside arena, else false
     */
    public boolean checkCoordinates(Tribut tribut) {
        return Math.sqrt(Math.pow(tribut.getX(), 2.0) + Math.pow(tribut.getY(), 2.0)) > 1.5;
    }
}