import io.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class MultiSearch {

    public static void main(String[] args) {

        File f = new File(args[0]);
        FileSystem fs = new FileSystem(f);

        // TODO change this to concurrent collection
        ArrayList<String> list = new ArrayList<>();

        MyFileVisitor v = new MyFileVisitor(list);
        fs.accept(v);

        for (String s : list) {
            // TODO extra thread for each file pls
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
    }
}