public class Volume extends Geometry implements Comparable {

    // Array to store all 4 corners of volume
    private Point[] corners;

    // Constructor
    public Volume(Point a, Point b) {
        super(2);
        corners = new Point[4];
        if (a.dimensions() != 2 || b.dimensions() != 2) {
            throw new IllegalArgumentException("wrong params!");
        }
        double ax = a.getCoords()[0];
        double ay = a.getCoords()[1];
        double bx = b.getCoords()[0];
        double by = b.getCoords()[1];
        for (int i = 0; i < 2; i++) {
            // coords cannot be perpendicular to axes
            if (ax == bx || ay == by) {
                throw new IllegalArgumentException("wrong params");
            }
        }
        this.corners[0] = a;
        this.corners[1] = b;
        if (getDelta(a, b) > 0) {
            corners[2] = new Point(searchMin(ax, bx), searchMin(ay, by));
            corners[3] = new Point(searchMax(ax, bx), searchMax(ay, by));
        }
        else {
            corners[2] = new Point(searchMin(ax, bx), searchMax(ax, bx));
            corners[3] = new Point(searchMax(ax, bx), searchMin(ax, bx));
        }
    }

    /**
     * @brief getter for corner coordinates
     * @return Point array
     */
    public Point[] getCorners() {
        return this.corners;
    }

    /**
     * @brief calculates gradient of line from Point a to b
     * @return double gradient
     */
    protected double getDelta(Point a, Point b) {
        double x1 = a.getCoords()[0];
        double y1 = a.getCoords()[1];
        double x2 = b.getCoords()[0];
        double y2 = b.getCoords()[1];
        return Math.abs(x1) - Math.abs(x2) / Math.abs(y1) - Math.abs(y2);
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
        double tmp = -Double.MAX_VALUE;
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
        double vx = 0;
        double vy = 0;
        double x1 = a.getCoords()[0];
        double y1 = a.getCoords()[1];
        double x2 = b.getCoords()[0];
        double y2 = b.getCoords()[1];
        if (x1 < 0) {
            vx = x1;
        }
        else {
            vx = -x1;
        }
        if (y1 < 0) {
            vy = y1;
        }
        else {
            vy = -y1;
        }
        // Construct new Point and calculate Distance with Pythagoras
        Point tmp = new Point(x2 + vx, y2 + vy);
        double x = tmp.getCoords()[0];
        double y = tmp.getCoords()[1];
        return Math.sqrt(x * x + y * y);
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
    public Volume encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) {
            throw new IllegalArgumentException("wrong params");
        }
        if (this.dimensions() != 2) {
            throw new RuntimeException("Action only applicable to 2 dimensional geometries");
        }
        if (other instanceof Volume) {
            Volume v = (Volume) other;
            // calculate longest distance between corners
            double tmp = 0;
            double range;
            Point mem1 = null;
            Point mem2 = null;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    range = getDistance(this.corners[i], v.getCorners()[j]);
                    if (range > tmp) {
                        tmp = range;
                        mem1 = this.corners[i];
                        mem2 = v.getCorners()[j];
                    }
                }
            }
            try {
                return new Volume(mem1, mem2);
            } catch (IllegalArgumentException e) {
                System.err.println("Points do not span a Volume");
            }
        }
        if (other instanceof Point) {
            Point p = (Point) other;
            double farest = 0;
            double range = 0;
            Point mem = null;
            for (int i = 0; i < 4; i++) {
                range = getDistance(this.corners[i], p);
                if (range > farest) {
                    farest = range;
                    mem = this.corners[i];
                }
            }
            try {
                return new Volume(p, mem);
            }
            catch (IllegalArgumentException e) {
                System.err.println("Points do not span a Volume!");
            }
        }
        return null;
    }

    /**
     * @brief calculates area of volume
     * @return double area
     */
    @Override
    public double volume() {
        return getDistance(corners[0], corners[2]) * getDistance(corners[1], corners[2]);
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