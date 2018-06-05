package genericList;

public class GenericTest {
    public static void main(String[] args) {

        GenericList list = new GenericList();

        list.add(new GenericEntry());

        if (list.getMods() != 1) {
            System.out.println("modcounter does not work");
        }

        // Tests if adding worked
        if (list.empty()) {
            System.out.println("Something went wrong when adding an element");
        } else {
            System.out.println("Adding an element worked");
        }

        GenericList clone = new GenericList();

        try {
            clone = list.clone();
        } catch (InternalError err) {
            System.out.println("Cloning does not work!");
        }


        // Tests if clone is empty. If it is, cloning did not work
        if (clone.empty()) {
            System.out.println("Clone should already have elements, cloning did not work. (Test 1/4)");
        } else {
            System.out.println("Cloning might have worked correctly (Test 1/4)");
        }

        // Tests if clone an list store the same reference. If they do, cloning did not work properly.
        if (clone == list) {
            System.out.println("Clone is not a real clone, cloning did not work. (Test 2/4)");
        } else {
            System.out.println("Cloning might have worked correctly. (Test 2/4)");
        }

        // Tests if clone and list have the same class. If they don't, cloning did not work properly.
        if (!(clone.getClass() == list.getClass())) {
            System.out.println("Clone and list are not instances of the same class, cloning did not work. (Test 34/)");
        } else {
            System.out.println("Cloning might have worked correctly (Test 3/4)");
        }

        // Tests if clone and list are equal. If they are not, cloning did not work.
        // TODO equals or clone does not work properly
        if (!clone.equals(list)) {
            System.out.println("Clone and list are not equal, cloning did not work. (Test 4/4)");
        } else {
            System.out.println("Cloning might have worked correctly (Test 4/4)");
        }

    }
}
