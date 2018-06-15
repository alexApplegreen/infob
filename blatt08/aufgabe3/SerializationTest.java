package aufgabe3;

import java.io.*;

import java.io.ObjectInputStream;

public class SerializationTest {
    public static void main(String[] args) {
        OpenHashSet<String> hash = new OpenHashSet<>(10);

        hash.insert("Hello");

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("hash.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            oos.writeObject(hash);
        } catch (IOException e) {
            e.printStackTrace();
        }



        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("hash.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        OpenHashSet<String> newHash = null;

        try {
            newHash = (OpenHashSet<String>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (!hash.equals(newHash)) {
            System.out.println("Something went wrong");
        }
    }
}
