package genericList;

public class IteratorTest {

    public static void main(String[] args) {

        boolean passed = true;

        GenericList<String> list = new GenericList<String>();
        list.add("Welt");
        list.add("Hallo");
        MyIterator iter = list.iterator();


        if (!iter.hasNext()) {
            passed = false;
            System.out.println("aggregate is found to be empty");
        }

        Throwable t = null;
        try {
            iter.remove();
        }
        catch (IllegalStateException e) {
            t = e;
        }
        if (t == null) {
            passed = false;
            System.out.println("Exception was not thrown");
        }

        if (!iter.next().equals("Hallo")) {
            passed = false;
            System.out.println("iterator did not find the correct element");
        }

        Throwable tr = null;
        try {
            iter.remove();
        }
        catch (IllegalStateException e) {
            tr = e;
        }
        if (tr != null) {
            passed = false;
            System.out.println("could not delete Object");
        }

        if (!iter.next().equals("Hallo")) {
            passed = false;
        }
        list.add("SomeString");

        Throwable t2 = null;
        try {
            iter.next();
        }
        catch (IllegalStateException e) {
            t2 = e;
        }
        if (t2 == null) {
            passed = false;
            System.out.println("Iterator did not fail on concurrent modification");
        }

        if (passed) {
            System.out.println("All tests passed");
        }
    }
}