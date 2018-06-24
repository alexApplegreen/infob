import io.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.*;

public class MultiSearch {

    // TODO write matches in concurrentlist
    public static void main(String[] args) {

        File f = new File(args[0]);
        FileSystem fs = new FileSystem(f);

        Queue<String> list = new ConcurrentLinkedQueue<String>();

        MyFileVisitor v = new MyFileVisitor(list);
        fs.accept(v);

        for (String s : list) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileReader fr = new FileReader(new File(s));
                        // TODO change regex to terminal arg
                        SearchLineReader reader = new SearchLineReader(fr, ".");

                        try {
                            String line = reader.readLine();
                            if (reader.getAmountOfMatches() > 0) {
                                System.out.println("Match in: " + s);
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
    }
}