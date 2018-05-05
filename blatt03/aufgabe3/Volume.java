public class Volume extends Geometry implements Comparable {

    // Array to store all 4 corners of volume
    private Point corners[];

    // Constructor
    public Volume(Point a, Point b) {
        super(2);

        if (a.dimensions() != 2 || b.dimensions() != 2) {
            throw new IllegalArgumentException("wrong params!");
        }

        double[] x = a.getCoords();
        double[] y = b.getCoords();

        for (int i = 0; i < 2; i++) {
            // coords cannot be perpendicular to axes
            if (x[i] == y[i]) {
                throw new IllegalArgumentException("wrong params");
            }
        }

        corners[0] = a;
        corners[1] = b;
        corners[2] = new Point(searchMin(x[0], y[0]), searchMin(x[1], y[1]));
        corners[3] = new Point(searchMax(x[0], y[0]), searchMax(x[1], y[1]));
    }

    /**
     * @brief getter for corner coordinates
     * @return Point array
     */
    public Point[] getCorners() {
        return this.corners;
    }

    /**
     * @brief searches for minimum in array
     * @param array of doubles
     * @return double minimum
     */
    protected double searchMin(double... array) {
        double tmp = Double.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (tmp > array[i]) {
                tmp = array[i];
            }
        }
        return tmp;
    }

    /**
     * @brief searches array for maximum
     * @param array
     * @return double maximum
     */
    protected double searchMax(double... array) {
        double tmp = 0;
        for (int i = 0; i < array.length; i++) {
            if (tmp < array[i]) {
                tmp = array[i];
            }
        }
        return tmp;
    }

    /**
     * @brief calculates euclidian distance between 2 points
     * @param a Point
     * @param b Point
     * @return double euclidian distance
     */
    protected double getDistance(Point a, Point b) {
        if (a.dimensions() != 2 || b.dimensions() != 2) {
            throw new IllegalArgumentException("wrong params");
        }

        double[] coordsA = a.getCoords();
        double[] coordsB = b.getCoords();
        double ax = coordsA[0];
        double ay = coordsA[1];
        double bx = coordsB[0];
        double by = coordsB[1];

        if (ax == bx) {
            return Math.abs(by - ay);
        }
        if (ay == by) {
            return Math.abs(bx - by);
        }
        // TODO euclidian distance between 2 Points
        return 0.0;
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