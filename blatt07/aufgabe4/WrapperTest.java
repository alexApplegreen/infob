package aufgabe4;

import java.io.*;

public class WrapperTest {
    public static void main(String[] args) {
        int[] array = new int[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }

        IntArrayWrapper wrapper = new IntArrayWrapper(array, "Test");

        System.out.println(wrapper.numberOfElements());

        for (int i = 0; i < wrapper.numberOfElements(); i++) {

            System.out.println(wrapper.getElement());
            wrapper.next();

        }

        wrapper.change(3, 5);

        wrapper.resetPos();

        for (int i = 0; i < wrapper.numberOfElements(); i++) {

            System.out.println(wrapper.getElement());
            wrapper.next();

        }
    }
}
