import io.*;
import java.io.*;
import java.util.*;

public class MyFileVisitor extends FileVisitorAdapter {

    private ArrayList<String> list;

    public MyFileVisitor(ArrayList<String> list) {
        super();
        this.list = list;
    }

    public FileVisitResult visitFile(File file) {
        this.list.add(file.getAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

}