package aufgabe4;

import java.io.*;

public class IntArrayWrapper {

    private int[] array;
    private int pos;
    private File file;
    private RandomAccessFile raf;

    public IntArrayWrapper(int[] arr, String name) {

        array = arr;
        pos = 0;
        file = new File(name);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("File could not be created");
            }
        }

        try {
            raf = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File does not exist");
        }

        for (int i = 0; i < array.length; i++) {
            try {
                raf.writeInt(array[i]);
            } catch (IOException e) {
                throw new RuntimeException("Could not write in File");
            }
        }
    }

    public IntArrayWrapper(String name) {
        File tmp = new File(name);
        if (tmp.exists()) {
            file = tmp;
            try {
                raf = new RandomAccessFile(file, "rw");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File does not exist");
            }
        } else {
            throw new RuntimeException("File not Found");
        }
    }

    public void next() {
        pos += 1;
    }

    public int getElement() {
        return array[pos];
    }

    public void change(int num, int pos) {
        array[pos - 1] = num;
        try {
            raf.seek(0);
        } catch (IOException e) {
            throw new RuntimeException("Could not go to begin of file");
        }
        try {
            raf.skipBytes((pos - 1) * 4);
        } catch (IOException e) {
            throw new RuntimeException("Could not skip to position");
        }

        try {
            raf.writeInt(num);
        } catch (IOException e) {
            throw new RuntimeException("Could not write in File");
        }
    }

    public void resetPos() {
        pos = 0;
    }

    public int numberOfElements() {
        return array.length;
    }

    public void close() {
        try {
            raf.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close file");
        }
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

}
