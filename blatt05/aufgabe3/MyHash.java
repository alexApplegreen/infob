import util.*;

public class MyHash<T> implements HashFunction<T> {

    public boolean equals(T o1, T o2) {
        Object a = (Object)o1;
        Object b = (Object)o2;
        return a.equals(b);
    }

    public int hashCode(T o) {
        Object a = (Object)o;
        return a.hashCode();
    }
}
