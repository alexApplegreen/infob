package util;

public class FileSystemVisitor<T> implements Visitor<T> {

    private enum RunMode {
        CONTINUE,
        SKIP,
        BREAK
    }

    private boolean continues;

    public FileSystemVisitor() {
        this.continues = true;
    }

    public boolean visit(T o) {
        if (o != null && continues) {
            this.continues = true;
            System.out.println(o);
            return true;
        }
        else {
            this.continues = false;
            return false;
        }
    }
}