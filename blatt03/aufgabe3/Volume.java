public class Volume extends Geometry implements Comparable {

    // Array to store all 4 corners of volume
    private Point corners[];

    // Constructor
    public Volume(Point a, Point b) {
        super(2);
        corners[0] = a;
        corners[1] = b;
        // TODO calculate remaining corners
    }

    /**
     * @brief propagates dimensions
     * @return int dimensions of volume
     */
    @Override
    public int dimensions() {
        return 2;
    }

    /**
     * @brief builds minimum bounding box around this and other
     * @param other Geometry to encapsulate
     * @return Geometry
     */
    @Override
    public Geometry encapsulate(Geometry other) {
        // TODO Volume.encpasulate()
        return null;
    }

    /**
     * @brief calculates area of volume
     * @return double area
     */
    @Override
    public double volume() {
        // TODO calculate Volume
        //double a = Math.abs
        return 0.0;
    }

    /**
     * @overrides Object.compareTo()
     * @param o
     * @return difference in volume
     */
    public int compareTo(Object o) {
        if (! (o instanceof Geometry)) {
            throw new IllegalArgumentException("wrong params");
        }
        Geometry geo = (Geometry) o;
        return (int) (this.volume() - geo.volume());
    }

}