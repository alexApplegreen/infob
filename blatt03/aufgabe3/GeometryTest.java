public class GeometryTest {

    public static void main(String[] args) {

        boolean passed = true;

        //__________ Test Point Methods __________\\

        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(5.0, 5.0);
        Point p4 = new Point(5.0, 5.0);
        if (p1 == null || p2 == null) {
            passed = false;
            System.out.println("Point Constructor does not work");
        }
        double p1x = p1.getCoords()[0];
        double p1y = p1.getCoords()[1];
        if (p1x != 0.0 || p1y != 0.0) {
            passed = false;
            System.out.println("getCoords / Point construcotr does not work");
        }
        if (p1.dimensions() != 2) {
            passed = false;
            System.out.println("dimensions does not work");
        }
        if (p1.volume() != 0) {
            passed = false;
            System.out.println("volume does not work");
        }
        if (p1.compareTo(p2) != 0) {
            passed = false;
            System.out.println("compareTo does not work");
        }
        try {
            Volume g = p1.encapsulate(p2);
        }
        catch (IllegalArgumentException e) {
            passed = false;
            System.out.println("Construction via Point.encapsulate failed: ");
        }
        catch (Throwable t) {
            passed = false;
            System.out.println("Construction of volume via Point.encapsulate failed");
        }
        if (p2.compareTo(p4) != 0) {
            passed = false;
            System.out.println("Point.compareTo does not work");
        }
        if (passed) {
            System.out.println("Point all tests passed");
        }

        //__________ Test Volume methods __________\\

        passed = true;

        try {
            Volume v = new Volume(p1, p2);
            if (v.getCorners().length != 4) {
                passed = false;
                System.out.println("Volume.getCoords() does not work");
            }
            if (v.dimensions() != 2) {
                passed = false;
                System.out.println("Volume.dimensions does not work");
            }
            Point p3 = new Point(7.0, 7.0);
            try {
                Volume v2 = v.encapsulate(p3);
                Volume g = p1.encapsulate(p2);
                if (v.compareTo(g) != 0) {
                    passed = false;
                    System.out.println("Volume.compareTo does not work");
                }
                if (g.volume() != 25) {
                    passed = false;
                    System.out.println("volume.volume() and/or encapsulate(Point) does not work");
                }
            }
            catch (Throwable t) {
                passed = false;
                System.out.println("Volume.encapsulate false negative");
            }
        }
        catch (Throwable t) {
            passed = false;
            System.out.println("Volume Construction failed");
        }
        if (passed) {
            System.out.println("Volume All tests passed");
        }
    }
}