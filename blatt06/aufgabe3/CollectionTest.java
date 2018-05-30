import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {

        Tester linkedTest = new Tester(new LinkedList<String>());
        Tester arrayTest = new Tester(new ArrayList<String>());
        Tester hashTest = new Tester(new HashSet<String>());

        System.out.println(linkedTest.testAll());
        System.out.println(arrayTest.testAll());
        System.out.println(hashTest.testAll());
    }
}
