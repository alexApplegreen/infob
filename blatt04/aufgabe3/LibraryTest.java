import util.*;

public class LibraryTest {

    public static void main(String[] args) {

        boolean passed = true;

        Library lib = new Library();
        BluRay sw1 = new BluRay("Star Wars Episode I", "George Lucas");
        BluRay sw2 = new BluRay("Star Wars Episode II", "George Lucas");

        Book got = new Book("Das Lied von Eis und Feuer", "George RR Martin");

        if (got.isBorrowed()) {
            passed = false;
            System.out.println("isBorrowed() does not work");
        }

        if (!sw1.getDescription().equals("George Lucas - Star Wars Episode I")) {
            passed = false;
            System.out.println("BluRay.getDescription does not work");
        }

        if (!got.getDescription().equals("George RR Martin - Das Lied von Eis und Feuer")) {
            passed = false;
            System.out.println("Book.getDescription does not work");
        }

        if (lib == null || sw1 == null || sw2 == null) {
            passed = false;
            System.out.println("Construction of lib or BluRay failed");
        }
        lib.addItem(sw1);
        lib.addItem(sw2);
        lib.addItem(got);

        // Test length of results List
        List results = lib.search("Star Wars");
        int counter = 0;
        while (!results.endpos()) {
            counter++;
            results.advance();
        }
        if (counter != 1) {
            passed = false;
            System.out.println("Library.search does not work");
        }

        lib.deleteItem(got);
        Throwable t = null;
        results = lib.search("Eis und Feuer");
        if (!results.empty()) {
            passed = false;
            System.out.println("found deleted item");
        }
        if (passed) {
            System.out.println("all tests passed");
        }
    }
}
