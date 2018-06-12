package aufgabe2;

public class VisitorTest {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        MyVisitor visitor = new MyVisitor();

        list.accept(visitor);

    }
}
