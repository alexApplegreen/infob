import util.*;
import java.lang.Math.*;

public class ObjectHashing<T> implements HashSet<T> {


    private List[] set;
    private MyHash myhash;

    // Constructor
    public ObjectHashing() {
        set = new List[10];
        // instanciate 10 Lists inside Array
        for (int i = 0; i < 10; i++) {
            set[i] = new List();
        }
        myhash = new MyHash();
    }

    /**
     * @brief checks if generic o is contained
     * @param o
     * @return boolean true if o is contained, else false
     */
    public boolean contains(T o) {
        int key = this.generateKey(o);
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
    public boolean insert(T o) {
        int key = this.generateKey(o);
        List container = set[key];
        while(!container.endpos()) {
            container.advance();
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
    public boolean delete(T o) {
        if(this.contains(o)) {
            int key = this.generateKey(o);
            List container = set[key];
            while(!container.endpos()) {
                if(container.elem().equals(o)) {
                    container.delete();
                    return true;
                }
                else {
                    container.advance();
                }
            }
            return false;
        }
        else {
            throw new RuntimeException("Object is not included in set");
        }
    }

    /**
     * @brief helper function for hashing
     * @param o generic
     * @return int hashcode of generic
     */
    private int generateKey(T o) {
        return Math.abs(myhash.hashCode(o) % 10);
    }
}
