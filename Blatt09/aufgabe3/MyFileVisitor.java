import io.*;
import java.io.*;
import java.util.concurrent.*;

public class MyFileVisitor extends FileVisitorAdapter {

    private ConcurrentLinkedQueue<String> list;

    public MyFileVisitor(ConcurrentLinkedQueue<String> list) {
        super();
        this.list = list;
    }

    public FileVisitResult visit(File file) {
        this.list.add(file.getName());
        return FileVisitResult.CONTINUE;
    }

}