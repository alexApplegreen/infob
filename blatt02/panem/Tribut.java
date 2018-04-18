import java.util.Random;
public class Tribut {

    private double x;
    private double y;

    // Constructor
    public Tribut(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // Constructor that generates random coordinates inside the Arena
    public Tribut() {
        Random rand = new Random();
        int alpha = rand.nextInt(360) + 1;
        double scale = Math.random() + 0.1;
        double x = scale * Math.cos(Math.toRadians(alpha));
        double y = scale * Math.sin(Math.toRadians(alpha));
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}