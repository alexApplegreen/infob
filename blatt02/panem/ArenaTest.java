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
        Arena arena2 = new Arena(katniss);

        if(arena2.checkCoordinates(katniss)) {
            passed = false;
            System.out.println("Error finding lost tribute");
        }

        for (int i = 0; i < tributes.length; i++) {
            System.out.println(arena.getArea(tributes[i]));
        }

        if(passed) {
            System.out.println("All tests passed.");
        }
    }
}
