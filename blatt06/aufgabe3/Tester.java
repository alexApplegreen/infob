import java.util.*;

public class Tester {

    // The collection to test
    private AbstractCollection collection;

    // Constructor
    public Tester(AbstractCollection collection) {
        this.collection = collection;
    }

    /**
     * @brief test 100 iterations of add operation
     * @return long median of time needed
     */
    public long testAdd() {
        long timeSum = 0;
        for (int i = 0; i < 100; i++) {
            long timeStampA = System.nanoTime();
            this.collection.add(new String(this.getRandom()));
            long timeStampB = System.nanoTime();
            timeSum += timeStampB - timeStampA;
        }
        return timeSum / 100;
    }

    /**
     * @brief test 100 iterations of remove operation.
     *        fills up collection if needed
     * @return long median of time needed
     */
    public long testRemove() {
        this.fill();
        long timeSum = 0;
        for (int i = 0; i < 100; i++) {
            long timeStampA = System.nanoTime();
            this.collection.remove(this.getRandom());
            long timeStampB = System.nanoTime();
            timeSum += timeStampB - timeStampA;
        }
        return timeSum / 100;
    }

    /**
     * @brief test 100 iterations of contain operation
     *        fills up collection if needed
     * @return long median of time needed
     */
    public long testContains() {
        this.fill();
        long timeSum = 0;
        for (int i = 0; i < 100; i++) {
            long timeStampA = System.nanoTime();
            this.collection.contains(this.getRandom());
            long timeStampB = System.nanoTime();
            timeSum += timeStampB - timeStampA;
        }
        return timeSum / 100;
    }

    /**
     * @brief tests all 3 operations
     * @return String representation of measured results
     */
    public String testAll() {
        return this.collection.getClass() + ": add(): " + this.testAdd() +
               " ns | remove(): " + this.testRemove() +
               " ns | contains: " + this.testContains() + " ns";
    }

    /**
     * @brief fills collection to 100 items if needed
     */
    private void fill() {
        if (this.collection.size() != 100) {
            for (int i = 0; i < 100; i++) {
                this.collection.add(new String(this.getRandom()));
            }
        }
    }

    /**
     * @brief returns emtpy String
     * @return String
     */
    private String getRandom() {
        return "";
    }
}
