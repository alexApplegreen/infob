public class StringStackTest {

    public static void main(String[] args) {

        boolean passed = true;

        StringStack stack = new StringStack();
        String a = "Welt"; stack.push(a);
        String b = "Hallo"; stack.push(b);
        StringStack copy = new StringStack(stack);

        // Test integrity of built stack
        if (!stack.peek().matches("Hallo")) {
            passed = false;
            System.out.println("Error building stack");
        }

        // Test stack.empty()
        if (stack.empty()) {
            passed = false;
            System.out.println("Error while pushing to stack");
        }

        // Test stack.peek()
        String stacktest = stack.peek();
        stack.pop();
        stacktest += stack.peek();
        if (!stacktest.matches("HalloWelt")) {
            passed = false;
            System.out.println("Error while peeking from stack");
        }

        // Test stack.pop()
        stack.pop();
        if (!stack.empty()) {
            passed = false;
            System.out.println("Error while deleting from stack");
        }

        // Check if copy was successful
        if (copy.empty()) {
            passed = false;
            System.out.println("Error while copying");
        }

        // Test copy.peek()
        if (!copy.peek().matches("Hallo")) {
            passed = false;
            System.out.println("Error building copy");
        }

        // Test if copy is complete
        String test = copy.peek();
        copy.pop();
        test += copy.peek();
        if (!test.matches("HalloWelt")) {
            passed = false;
            System.out.println("Error while peeking from copy");
        }

        // Test copy.pop()
        copy.pop();
        if (!copy.empty()) {
            passed = false;
            System.out.println("Error while deleting from copy");
        }

        if (passed) {
            System.out.println("Alle tests erfolgreich");
        }
    }
}