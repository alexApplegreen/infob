
public class Point extends Geometry implements Comparable {

    // Array of coordinates
    private double[] coordinates;

    // Constructor
    public Point(double... coordinates) {
        super(coordinates.length);
        this.coordinates = coordinates;
    }

    /**
     * @brief return all coordinates
     * @return double array
     */
    public double[] getCoords() {
        return this.coordinates;
    }

    /**
     * @brief calculates euclidian distance to other Point
     * @param other Point
     * @return double euclidian distance
     */
    protected double getDistance(Point other) {
        // Vector to move both points
        double vx = 0;
        double vy = 0;
        // Coordinates of Point p1 and p2
        double x1 = this.coordinates[0];
        double y1 = this.coordinates[1];
        double x2 = other.getCoords()[0];
        double y2 = other.getCoords()[1];
        // move this to E, adjust other accordingly
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
        double a = tmp.getCoords()[0];
        double b = tmp.getCoords()[1];
        return Math.sqrt(a * a + b * b);
    }

    /**
     * @brief propagates dimension
     * @return int
     */
    @Override
    public int dimensions() {
        return this.coordinates.length;
    }

    /**
     * @brief builds minimum bounding box around Geometry
     * @param other Geometry to encapsulate
     * @return Minimum bounding box
     */
    @Override
    public Volume encapsulate(Geometry other) {
        // Geometries need to share dimensional properties
        if (this.dimensions() != other.dimensions()) {
            throw new IllegalArgumentException("wrong params");
        }
        // Action is not applicable for < 2 dimensional geometries
        if (this.dimensions() == 2) {
            if (other instanceof Point) {
                Point p = (Point) other;
                return new Volume(this, p);
            }
            if (other instanceof Volume) {
                Volume v = (Volume) other;
                double farest = 0;
                Point[] corners = v.getCorners();
                Point tmp = null;
                // Check distance to all 4 corners of other
                for (int i = 0; i < 4; i++) {
                    double distance = getDistance(corners[i]);
                    if (distance > farest) {
                        farest = distance;
                        tmp = corners[i];
                    }
                }
                // Build Geometry with Points which are the farest apart
                try {
                    // constructing might fail if the 2 points are perpendicular to axes
                    return new Volume(this, tmp);
                } catch (IllegalArgumentException e) {
                    System.err.println("Points do not span a Volume!");
                }
            }
        }
        return null;
    }

    /**
     * @brief calculates Area / volume of Geometry Points have no volume
     * @return volume in double
     */
    @Override
    public double volume() {
        return 0.0;
    }

    /**
     * @overrides object.compareTo
     * @param o Geometry to compare this to
     * @return difference of volumes
     * @throws IllegalArgumentException if o is no instance of Geometry
     */
    public int compareTo(Object o) {
        if (! (o instanceof Geometry)) {
            throw new IllegalArgumentException("wrong params");
        }
        Geometry geo = (Geometry) o;
        return (int) (this.volume() - geo.volume());
    }

    /**
     * @brief builds string representation of point
     * @return String of coordinates
     */
    public String toString() {
        String result = "";
        for (double coordinate : this.coordinates) {
            result += coordinate + " ";
        }
        return result;
    }
}