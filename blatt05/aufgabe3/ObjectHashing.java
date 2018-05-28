import util.*;

public class ObjectHashing<T> implements HashSet {


    private List[] set;

    // Constructor
    public ObjectHashing() {
        // instanciate 10 Lists inside Array
        for (int i = 0; i < 10; i++) {
            set[i] = new List();
        }
    }

    /**
     * @brief checks if generic o is contained
     * @param o
     * @return boolean true if o is contained, else false
     */
    @Override
    public boolean contains(T o) {
        int key = generateKey(o);
        List container = set[key];
        while(!container.endpos()) {
            if (container.elem().equals(o)) {
                return true;
            }
            else {
                container.advance();
            }
        }
        return false;
    }

    /**
     * @brief inserts generic inside HashSet
     * @param o generic
     * @return boolean true if insertion was successful, else false
     */
    @Override
    public boolean insert(T o) {
        int key = generateKey(o);
        List container = set[key];
        while(!container.endpos()) {
            advance();
        }
        // TODO check if this can possibly fail
        container.add(o);
        return true;
    }

    /**
     * @brief deletes generic inside HashSet
     * @param o generic
     * @return boolean true if deletion was successful, else false
     */
    @Override
    public boolean delete(T o) {
        if(this.contains(o)) {
            int key = generateKey(o);
            List container = set[key];
            while(!container.endpos()) {
                if(container.elem().equals(o)) {
                    container.delete();
                }
                else {
                    container.advance();
                }
            }
        }
    }

    private int generateKey(T o) {
        return T.hashCode() % 10;
    }
}
