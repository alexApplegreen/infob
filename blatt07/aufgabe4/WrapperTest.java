package aufgabe4;

public class WrapperTest {
    public static void main(String[] args) {
        boolean passed = true;
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        IntArrayWrapper wrapper1 = new IntArrayWrapper(array, "Test");

        if (array.length != wrapper1.numberOfElements()) {
            System.out.println("Instanciation did not work correctly");
            passed = false;
        }

        if (wrapper1.getPos() != 0) {
            System.out.println("getPos() does not work or constructor does not initialize pos correctly");
            passed = false;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != wrapper1.getElement()) {
                System.out.println("Elements are not correct");
                passed = false;
            }

            if (wrapper1.getPos() != i) {
                System.out.println("next() does not work");
                passed = false;
            }

            wrapper1.next();
        }

        wrapper1.resetPos();
        if (wrapper1.getPos() != 0) {
            System.out.println("resetPos() does not work");
            passed = false;
        }

        wrapper1.change(3, 5);
        for (int i = 0; i < array.length; i++) {
            if (i == 5) {
                if (wrapper1.getElement() != 3) {
                    System.out.println("change() does not work");
                    passed = false;
                }
            }
            wrapper1.next();
        }

        wrapper1.close();
        try {
            wrapper1.change(2, 4);
            System.out.println("Closing file does not work");
            passed = false;
        } catch (RuntimeException e) {
        }

        IntArrayWrapper wrapper2 = new IntArrayWrapper("Test");

        try {
            wrapper2.change(2, 3);
        } catch (RuntimeException e) {
            System.out.println("Opening file from new IntArrayWrapper does not work");
            passed = false;
        }


        if (passed) {
            System.out.println("All tests passed");
        }
    }
}
