import io.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyFileVisitor extends FileVisitorAdapter {

    private ArrayList<File> list;
    private boolean recursive;

    public MyFileVisitor(ArrayList<File> list, boolean recursive) {
        super();
        this.list = list;
        this.recursive = recursive;
    }

    public FileVisitResult visitFile(File file) {
        this.list.add(file);
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult PreVisitDirectory(File dir) {
        if (this.recursive) {
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

}