import io.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.*;

public class MultiSearch {

    private boolean recursive;
    private File f;
    private String regex;

    /**
     * @brief constructor
     * @param recursive boolean walk subdirs or don't
     * @param filepath String rootdir
     * @param regex String pattern to search
     */
    public MultiSearch(boolean recursive, String filepath, String regex) {
        this.recursive = recursive;
        this.f = new File(filepath);
        this.regex = regex;
    }

    /**
     * @brief perform parallel search in directory
     */
    public void run() {

        FileSystem fs = new FileSystem(f);

        Queue<String> queue = new ConcurrentLinkedQueue<String>();
        ArrayList<File> list = new ArrayList<>();

        // Use custom visitor to add every file in dir to arraylist
        MyFileVisitor v = new MyFileVisitor(list, recursive);
        fs.accept(v);

        // new thread for every file in rootdir
        for (File file : list) {

            Thread t = new Thread(new Runnable() {

                /**
                 * @brief starts searches File line by line for given pattern and adds filename to queue if match is
                 *        found
                 */
                @Override
                public void run() {
                    try {
                        FileReader fr = new FileReader(file);
                        SearchLineReader reader = new SearchLineReader(fr, MultiSearch.this.regex);

                        try {
                            String line = reader.readLine();
                            if (reader.getAmountOfMatches() >= 1) {
                                queue.add(file.getName());
                            }
                        } catch (IOException e) {
                            System.out.println("reader.readline() cannot read file");
                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println("cannot instantiate Filereader");
                    }
                }
            });

            t.start();
        }

        // print every entry in queue
        for (String s : queue) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        boolean recursive = false;
        String path = null;
        String pattern = null;

        if (args.length < 1) {
            System.out.println("java MultiSearch [-r] {path} [-p pattern]");
        }

        int i = 0;
        do {
            if (args[i].equals("-r")) {
                recursive = true;
            }
            if (args[i].equals("-p")) {
                pattern = args[i+1];
                i++;
            } else {
                path = args[i];
            }
            i++;
        } while (i < args.length);

        if (path == null) {
            throw new RuntimeException("No File given!");
        }
        if (pattern == null) {
            pattern = "";
        }

        MultiSearch ms = new MultiSearch(recursive, path, pattern);
        ms.run();
    }
}