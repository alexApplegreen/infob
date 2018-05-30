package genericList;

public class MyIterator<T> implements Iterator<T> {

    private int modCount;
    private GenericList<T> aggregate;
    private int pos;
    private boolean isAllowedToRemove;

    public MyIterator<T>(int modCount, GenericList<T> list) {
        this.modCount = modCount;
        aggregate = list;
        pos = 0;
        isAllowedToRemove = false;
    }

    public boolean hasNext() {
        return !aggregate.empty();
    }

    public T next() {
        isAllowedToRemove = true;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = aggregate.elem();
        this.pos++;
        aggregate.advance();
        return result;
    }

    public void remove() {
        if (!isAllowedToRemove) {
            throw new IllegalStateException();
        }
        aggregate.reset();
        for (int i = 0; i < this.pos - 1; i++) {
            aggregate.advance();
            aggregate.delete();
        }
    }
}