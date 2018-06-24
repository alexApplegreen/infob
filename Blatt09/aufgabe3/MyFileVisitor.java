import io.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyFileVisitor extends FileVisitorAdapter {

    private Queue<String> list;

    public MyFileVisitor(Queue<String> list) {
        super();
        this.list = list;
    }

    public FileVisitResult visitFile(File file) {
        this.list.add(file.getAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

}