import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SearchFile {

    private String regex;
    private String path;
    private boolean recursive;
    private File file;
    private ArrayList<String> list;
    private Pattern pattern;

    /**
     * @brief Constructor
     * @param regex String pattern to search for
     * @param path String root dorectory to search in
     * @param recursive boolean strategy
     */
    public SearchFile(String regex, String path, boolean recursive) {
        this.regex = regex;
        if (regex == null) {
            this.regex = ".";
        }
        this.path = path;
        this.recursive = recursive;
        this.file = new File(this.path);
        this.list = new ArrayList<String>();
        this.pattern = Pattern.compile(this.regex);
    }

    /**
     * @brief searches current/subdirectories for files
     * @param file File object as root dir
     * @param sublevel int recursionlevel
     * @throws IOException in case rootdir does not exist
     */
    public void search(File file, int sublevel) throws IOException {
        if (!this.file.exists()) {
            throw new IOException("File does not exist");
        }
        String indent = "";
        for (int j = 0; j < sublevel; j++) {
            indent += "    ";
        }
        File[] ls = file.listFiles();
        for (int i = 0; i < ls.length; i++) {
            if (ls[i].isDirectory()) {
                Matcher matcher = this.pattern.matcher(ls[i].getName());
                if (matcher.find()) {
                    this.list.add(indent + ls[i].getAbsolutePath());
                }
                if (this.recursive) {
                    search(ls[i], sublevel++);
                }
            } else if (ls[i].isFile()) {
                Matcher matcher = this.pattern.matcher(ls[i].getName());
                if (matcher.find()) {
                    this.list.add(indent + ls[i].getAbsolutePath());
                }
            }
        }
    }

    /**
     * @brief getter for ArrayList field
     * @return ArrayList of results
     */
    public ArrayList<String> getList() {
        return this.list;
    }

    /**
     * @brief returns string representation of usage for SearchFile class
     * @return string usage
     */
    public static String usage() {
        return "java search [-r] [-p pattern] {directory}";
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException(SearchFile.usage());
        }
        boolean recursive = false;
        String pattern = null;
        String filename = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("-r")) {
                recursive = true;
            } else {
                recursive = false;
            }
            if (args[i].contains("-p")) {
                pattern = args[i + 1];
                i++;
            }
        }
        filename = args[args.length - 1];

        File root = new File(filename);
        SearchFile sf = new SearchFile(pattern, filename, recursive);
        try {
            sf.search(root, 0);
            for (String s : sf.getList()) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}