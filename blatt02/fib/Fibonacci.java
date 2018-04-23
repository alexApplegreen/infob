public class Fibonacci {

    private int iteration = 0;
    private int prev;
    private int prevprev;
    private int result;

    public int next() {
        if (iteration == 0) {
            iteration++;
            this.result = 0;
            return 0;
        }
        if (iteration == 1) {
            this.prevprev = 0;
            this.prev = 1;
            this.result = 1;
            this.iteration++;
            return 1;
        }
        int temp = this.prev + this.prevprev;
        this.prevprev = this.prev;
        this.prev = temp;
        return temp;
    }
}
