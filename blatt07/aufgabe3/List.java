import java.util.*;
import java.io.*;
import util.*;
import genericList.*;

public class List {

    // simple list for iterative strategy
    private GenericList<String> rootfiles;

    // Visitor
    private Visitor<String> v;

    // rootdir
    private File root;

    // constructor
    public List(String path) {
        this.root = new File(path);
        //this.v = new MyVisitor();
    }

    /**
     * @brief scan root dir for files, use "-r" for recursively searching subdirs
     * @param args
     */
    public ArrayList<String> scan(String path, ArrayList<String> results) {
        File[] ls = root.listFiles();
        for (int i = 0; i < ls.length; i++) {
            if (ls[i].isDirectory()) {
                results.add(ls[i].getName());
                // TODO fix overflow in recursion
                return scan(path + ls[i].getName(), results);
            }
            else if (ls[i].isFile()) {
                results.add(ls[i].getName());
            }
        }
        return results;
    }

    public void run() {
        GenericList<String> results = new GenericList<>();
        ArrayList<String> arr = scan(this.root.getName(), new ArrayList<String>());
        for (String s : arr) {
            results.add(s);
        }
        //list.accept(v)
    }

    public static void main(String[] args) {
        List list = new List(args[0]);
        ArrayList<String> arr = list.run();
        for (String s : arr) {
            System.out.println(s);
        }
    }
}