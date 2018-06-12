package aufgabe4;

import java.io.*;

//TODO JavaDoc!


/**
 * Makes it possible to save an int-Array persistently, to iterate over it and change it.
 */
public class IntArrayWrapper {

    private int[] array;
    private int pos;
    private File file;
    private RandomAccessFile raf;

    /**
     * @brief Constructor for a new array
     * @param arr
     * @param name of the file the array is saved to
     */
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

    /**
     * @brief Constructor using an existing file
     * @param name
     */
    public IntArrayWrapper(String name) {
        File file = new File(name);
        if (file.exists()) {
            try {
                raf = new RandomAccessFile(file, "rw");
                int length = (((int)raf.length()) / 4);
                array = new int[length];
                for (int i = 0; i < length; i++) {
                    array[i] = raf.readInt();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File does not exist");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("File not Found");
        }
    }

    /**
     * @brief Changes the position to the next element
     */
    public void next() {
        pos += 1;
    }

    /**
     * Returns the element on the current position of the array
     * @return
     */
    public int getElement() {
        return array[pos];
    }

    /**
     * Changes the value on the given position to the given number
     * @param num
     * @param pos
     */
    public void change(int num, int pos) {
        array[pos] = num;
        try {
            raf.seek(0);
        } catch (IOException e) {
            throw new RuntimeException("Could not go to begin of file");
        }
        try {
            raf.skipBytes((pos) * 4);
        } catch (IOException e) {
            throw new RuntimeException("Could not skip to position");
        }

        try {
            raf.writeInt(num);
        } catch (IOException e) {
            throw new RuntimeException("Could not write in File");
        }
    }

    /**
     * Resets the position to 0
     */
    public void resetPos() {
        pos = 0;
    }

    /**
     * Returns current position
     * @return
     */
    public int getPos(){
        return pos;
    }

    /**
     * Returns the number of elements in the array
     * @return
     */
    public int numberOfElements() {
        return array.length;
    }

    /**
     * Closes the RandomAccessFile
     */
    public void close() {
        try {
            raf.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close file");
        }
    }

    public boolean fileExists(String name) {
        File tmp = new File(name);
        return file.exists();
    }
}
