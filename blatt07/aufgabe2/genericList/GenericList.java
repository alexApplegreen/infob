package genericList;
import java.util.*;

// Erbt nicht, weil add() sonst Probleme macht bzgl override

public class GenericList<T> implements Cloneable, Iterable, Visitable {

    /**
     * Reference on the first Entry of this List
     */

    private GenericEntry begin;
    /**
     * References before the actual Entry of this List
     */
    private GenericEntry pos;

    /**
     * counts the modifications made to list
     */
    private int modCount;

    /**
     * Create a new empty List.
     */
    public GenericList() {
        pos = begin = new GenericEntry();
        this.modCount = 0;
    }

    /**
     * Determines if this List is empty or not.
     *
     * @return <code>true</code>, if there are no elements in this List
     */
    public boolean empty() {
        return begin.next == null;
    }

    /**
     * Determines if it is possible to {@link #advance()} in this List. Returns
     * <code>true</code> if the last position of this List has been reached. An
     * {@link #empty()} List will alway deliver <code>true</code>
     *
     * @return <code>true</code> if the last Entry in this List already has been
     *         reached.
     */
    public boolean endpos() { // true, wenn am Ende
        return pos.next == null;
    }

    /**
     * Returns to the beginning of this List.
     */
    public void reset() {
        pos = begin;
    }

    /**
     * Advances one step in this List.
     *
     * @throws RuntimeExcpetion
     *            if the last Entry of this List already has been reached.
     */
    public void advance() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        pos = pos.next;
    }

    /**
     * Returns the actual element of this List.
     *
     * @return the actual element
     *
     * @throws RuntimeException
     *            if the last Entry of this List already has been reached.
     */
    public T elem() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        return (T) pos.next.o;
    }

    /**
     * Inserts <code>o</code> in this List. It will be placed before the actual
     * element. After insertion the inserted element will become the actual
     * element.
     *
     * @param x
     *           the element to be inserted
     */
    public void add(T x) {
        GenericEntry newone = new GenericEntry(x, pos.next);
        this.modCount++;
        pos.next = newone;
    }

    /**
     * Deletes the actual element of this List. The element after the actual
     * element will become the new actual element.
     *
     * @throws RuntimeExcpetion
     *            if the last Entry of this List already has been reached.
     */
    public void delete() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
        this.modCount++;
        pos.next = pos.next.next;
    }

    public int getMods() {
        return this.modCount;
    }

    public void incMods() {
        this.modCount++;
    }

    public MyIterator<T> iterator() {
        return new MyIterator(this.getMods(), this);
    }

    public T accept(Visitor v) {
        // TODO
        return null;
    }


    public GenericList clone() {
        try {
            return (GenericList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
