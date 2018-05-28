public class HashingTest {

    public static void main(String[] args) {

        boolean passed = true;
        ObjectHashing<String> oh = new ObjectHashing<String>();

        String first = "Hallo Welt";
        int firsthash = first.hashCode() % 10;

        if(!oh.insert(first)) {
            passed = false;
            System.out.println("could not add to set");
        }
        if(!oh.contains(first)) {
            passed = false;
            System.out.println("could not find added object in set");
        }
        if(!oh.delete(first)) {
            passed = false;
            System.out.println("could not delete object from set");
        }
        if(oh.contains(first)) {
            passed = false;
            System.out.println("found object which should be deleted");
        }
        if(passed) {
            System.out.println("All tests passed");
        }
    }
}