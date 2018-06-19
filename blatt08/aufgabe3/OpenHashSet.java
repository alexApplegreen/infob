package aufgabe3;

import java.io.*;

/**
 * Implementation of an open hash set: one bucket holds all entries with the
 * same {@link Object#hashCode()}.
 */
public class OpenHashSet<T> implements HashSet<T>, Serializable {

    private MyList<T>[] buckets;
    private HashFunction<? super T> hashFunction;
    private static final long serialVersionUID = 34602942123982377L;

    /**
     * An <code>OpenHashSet</code> with a hash table of length 10.
     */
    public OpenHashSet() {
        this(10);
    }

    /**
     * An <code>OpenHashSet</code> with a hash table of length <code>size</code>
     *
     * @param size length of the hash table
     */
    public OpenHashSet(int size) {
        this(size, null);
    }

    /**
     * An <code>OpenHashSet</code> with a hash table of length <code>size</code>
     * which uses <code>hashFunction</code> to define the equality of two objects.
     *
     * @param size         length of the hash table
     * @param hashFunction representation of the used hash function
     */
    public OpenHashSet(int size, HashFunction<? super T> hashFunction) {
        this.buckets = new MyList[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new MyList<T>();
        }
        this.hashFunction = hashFunction;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        int size = buckets.length;
        out.writeInt(size);

        for (int i = 0; i < size; i++) {
            if (!buckets[i].empty()) {
                out.writeBoolean(true);
                out.writeObject(buckets[i]);
            } else {
                out.writeBoolean(false);
            }
        }

        if (hashFunction != null) {
            out.writeBoolean(true);
            out.writeObject(hashFunction);
        } else {
            out.writeBoolean(false);
        }

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        buckets = new MyList[size];

        for (int i = 0; i < size; i++) {
            if (in.readBoolean()) {
                buckets[i] = (MyList<T>) in.readObject();
            } else {
                buckets[i] = new MyList<>();
            }
        }

        if (in.readBoolean()) {
            hashFunction = (HashFunction) in.readObject();
        } else {
            hashFunction = null;
        }


    }

//   private void readObjectNoData() throws ObjectStreamException {
//
//   }

    @Override
    public boolean contains(T o) {
        MyList<T> bucket = buckets[hashCode(o) % buckets.length];
        bucket.reset();
        while (!bucket.endpos()) {
            if (equals(o, bucket.elem())) {
                return true;
            }
            bucket.advance();
        }
        return false;
    }

    @Override
    public boolean insert(T o) {
        if (contains(o)) {
            return false;
        } else {
            buckets[hashCode(o) % buckets.length].add(o);
            return true;
        }
    }

    @Override
    public boolean delete(T o) {
        MyList<T> bucket = buckets[hashCode(o) % buckets.length];
        bucket.reset();
        while (!bucket.endpos()) {
            if (equals(o, bucket.elem())) {
                bucket.delete();
                return true;
            }
            bucket.advance();
        }
        return false;
    }

    private int hashCode(T o) {
        return hashFunction == null ? o.hashCode() : hashFunction.hashCode(o);
    }

    private boolean equals(T o1, T o2) {
        if (hashFunction == null) {
            return o1.equals(o2);
        } else {
            return hashFunction.equals(o1, o2);
        }
    }
}
