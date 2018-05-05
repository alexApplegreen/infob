
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
        // TODO calculate distance from this to other point
        return 0.0;
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
    public Geometry encapsulate(Geometry other) {
        // Geometries need to share dimensional properties
        if(this.dimensions() != other.dimensions()) {
            throw new IllegalArgumentException("wrong params");
        }
        // Action is not applicable for < 2 dimensional geometries
        switch (this.dimensions()) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                if (other instanceof Point) {
                    return new Volume(this, other);
                }
                if (other instanceof Volume) {
                    double farest = 0;
                    Point[] corners = other.getCorners();
                    Point tmp;
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
                        Geometry result = new Geometry(this, tmp);
                    } catch (IllegalArgumentExeption e) {
                        System.out.err("Points do not span a Volume!");
                    }
                    return result;
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