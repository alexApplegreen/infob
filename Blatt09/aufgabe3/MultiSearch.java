import io.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class MultiSearch {

    public static void main(String[] args) {
        File f = new File(args[0]);
        FileSystem fs = new FileSystem(f);
        ConcurrentLinkedQueue list = new ConcurrentLinkedQueue();
        MyFileVisitor v = new MyFileVisitor(list);
        fs.accept(v);
    }
}