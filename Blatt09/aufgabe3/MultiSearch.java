import io.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.*;

public class MultiSearch {

    private boolean recursive;
    private File f;
    private String regex;

    public MultiSearch(boolean recursive, String filepath, String regex) {
        this.recursive = recursive;
        this.f = new File(filepath);
        this.regex = regex;
    }

    public void run() {

        FileSystem fs = new FileSystem(f);

        Queue<String> queue = new ConcurrentLinkedQueue<String>();
        ArrayList<File> list = new ArrayList<>();

        MyFileVisitor v = new MyFileVisitor(list, recursive);
        fs.accept(v);

        for (File file : list) {

            Thread t = new Thread(new Runnable() {

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

        for (String s : queue) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        // TODO parse args

        MultiSearch ms = new MultiSearch(true, args[0], "");
        ms.run();
    }
}