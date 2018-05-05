
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
        if(this.dimensions() != other.dimensions()) {
            throw new IllegalArgumentException("wring params");
        }
        switch (this.dimensions()) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                //TODO Point.encapsulate()
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