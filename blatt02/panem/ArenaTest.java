public class ArenaTest {

    public static void main(String[] args) {

        boolean passed = true;

        Tribut[] tributes = new Tribut[3];
        // Build Arena with 3 random tributes
        for (int i = 0; i < 3; i++) {
            Tribut tribute = new Tribut();
            tributes[i] = tribute;
        }

        Arena arena = new Arena(tributes);

        // Check if randomly generated tributes are inside arena
        for (int i = 0; i < 3; i++) {
            if(!arena.checkCoordinates(tributes[i])) {
                passed = false;
            }
        }

        // new tribute outside arena
        Tribut katniss = new Tribut(10.0, 10.0);

        // new tribute inside area 4
        Tribut ninty = new Tribut(1.0, 0.0);
        Arena arena2 = new Arena(katniss, ninty);

        // check if lost tribute is located outside arena
        if (arena2.checkCoordinates(katniss)) {
            passed = false;
            System.out.println("Error finding lost tribute");
        }

        // check if tribute in area 4 is located inside arena
        if (!arena2.checkCoordinates(ninty)) {
            passed = false;
            System.out.println("Tribute at 90 degrees not found!");
        }

        // check if tribute is located in correct area
        if (arena2.getArea(ninty) != 4) {
            passed = false;
            System.out.println("Tribute at 90 degrees found in wrong Area");
        }

        if(passed) {
            System.out.println("All tests passed.");
        }
    }
}
