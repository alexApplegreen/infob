import java.util.*;
import java.io.*;
import util.*;
import genericList.*;

public class List {

    private boolean recursive;

    // simple list for iterative strategy
    private GenericList<String> rootfiles;

    // Visitor
    private FileSystemVisitor<String> v;

    // rootdir
    private File root;
    private String path;

    // constructor
    public List(String[] args) {
        if (args.length != 2) {
            this.path = args[0];
        }
        else {
            this.recursive = args[0].toLowerCase().equals("-r");
            this.path = args[1];
        }
        this.root = new File(path);
        this.v = new FileSystemVisitor<String>();
    }

    /**
     * @brief scan root dir for files, use "-r" for recursively searching subdirs
     * @param args
     */
    public ArrayList<String> scan(String path, ArrayList<String> results, int subLevel) {

        File current = new File(path);
        File[] ls = current.listFiles();

        // for each File in root dir check if File is Directory
        for (int i = 0; i < ls.length; i++) {
            if (ls[i].isDirectory() && this.recursive) {
                // if file is a directory recursively search it
                results.add(ls[i].getName());
                scan(path + "/" + ls[i].getName(), results, subLevel + 1);
            }

            else {
                // indentation for files in sub directory
                String tmp = "";
                for (int j = 0; j < subLevel; j++) {
                    tmp += "    ";
                }
                results.add(tmp + ls[i].getName());
            }
        }
        return results;
    }

    public void run() {
        GenericList<String> results = new GenericList<>();
        ArrayList<String> arr = scan(this.path, new ArrayList<String>(), 0);
        for (String s : arr) {
            results.add(s);
        }
        results.accept(v);
    }

    public static void main(String[] args) {
        List list = new List(args);
        list.run();
    }
}