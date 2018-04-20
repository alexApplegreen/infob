public class Fibonacci {

    private int iteration = 0;
    private int prev;
    private int prevprev;;
    private int result;

    public Fibonacci() {
    }

    public int next() {
        if (iteration == 0) {
            iteration++;
            return 0;
        }
        if (iteration == 1) {
            this.prevprev = 0;
            this.prev = 1;
            iteration++;
        }
        int result = this.prev + this.prevprev;
        this.prevprev = this.prev;
        this.prev = this.result;
        return result;
    }
}
