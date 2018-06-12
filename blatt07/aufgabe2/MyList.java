package aufgabe2;

import aufgabe2.util.Visitor;

import java.util.Iterator;
import java.util.NoSuchElementException;

import aufgabe2.util.*;


public class MyList<E> implements Cloneable, Iterable<E>, Visitable {

    /**
     * Reference on the first Entry of this List
     */
    private MyEntry<E> begin;
    private int modCount;
    /**
     * References before the actual Entry of this List
     */
    private MyEntry<E> pos;

    /**
     * Create a new empty List.
     */
    public MyList() {
        pos = begin = new MyEntry<E>();
        modCount = 0;
    }


    /**
     * Returns the current modification count of this list. It may be increased by
     * {@link #increaseModCount()}
     *
     * @return the current modification count
     */
    public int getModCount() {
        return this.modCount;
    }

    /**
     * Increases the current modification count of this list.
     */
    public void increaseModCount() {
        this.modCount++;
    }


    /**
     * Iterates through the list as long as the visitor returns true and the end of the list is not reached.
     *
     * @param v Visitor that is accepted to visit the list
     */
    @Override
    public void accept(Visitor v) {
        MyEntry<E> current = this.begin.next;
        while (current != null && v.visit(current.o)) {
            current = current.next;
        }
    }


    /**
     * Inserts <code>o</code> in this List. It will be placed before the actual
     * element. After insertion the inserted element will become the actual
     * element.
     *
     * @param x the element to be inserted
     */
    public void add(E x) {
        MyEntry<E> newone = new MyEntry<E>(x, pos.next);

        pos.next = newone;
        this.increaseModCount();
    }

    /**
     * Advances one step in this List.
     *
     * @throws NoSuchElementException if the last Entry of this List already has
     *                                been reached.
     */
    public void advance() {
        if (endpos()) {
            throw new NoSuchElementException("Already at the end of this List");
        }
        pos = pos.next;
    }

    /**
     * Clones this MyList. Will create a new independent MyList which actual
     * position lies at the beginning of this MyList. This clone operation also
     * fulfills the optional requirements defined by the {@link Object#clone()}
     * operation. NOTE: Inserted elements will not be cloned, due to the fact,
     * that the {@link Object#clone()} is <code>protected</code>.
     *
     * @see Object#clone()
     */
    @Override
    public MyList<E> clone() {
        try {

            MyList<E> clone = (MyList<E>) super.clone();
            clone.begin = this.begin.clone();
            clone.pos = clone.begin;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * Deletes the actual element of this List. The element after the actual
     * element will become the new actual element.
     *
     * @throws RuntimeException if the last Entry of this List already has been
     *                          reached.
     */
    public void delete() {
        if (endpos()) {
            throw new NoSuchElementException("Already at the end of this List");
        }
        this.increaseModCount();
        pos.next = pos.next.next;
    }

    /**
     * Returns the actual element of this List.
     *
     * @return the actual element
     * @throws RuntimeException if the last Entry of this List already has been
     *                          reached.
     */
    public E elem() {
        if (endpos()) {
            throw new NoSuchElementException("Already at the end of this List");
        }
        return pos.next.o;
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
     * reached.
     */
    public boolean endpos() { // true, wenn am Ende
        return pos.next == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyList other = (MyList) obj;
        if (other.begin != null)
            return false;
        if (!begin.equals(other.begin))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((begin == null) ? 0 : begin.hashCode());
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator(this, this.begin);
    }

    /**
     * Returns to the beginning of this List.
     */
    public void reset() {
        pos = begin;
    }
}