import java.io.*;
import java.lang.*;
import java.util.*;

public class FileWatcher extends TimerTask {

    private File file;
    private long interval;
    private Timer timer;
    private long lastSize;

    /**
     * @brief constructor
     * @param path String to file
     * @param interval long delay between scans
     */
    public FileWatcher(String path, long interval) {
        // check if intervak is valid
        if (interval < 0) {
            this.interval = 1000;
        } else {
            this.interval = interval;
        }

        this.file = new File(path);
        if (!this.file.exists()) {
            throw new RuntimeException("File does not exist");
        }

        this.lastSize = 0L;

        // schedule timertask
        this.timer = new Timer();
        timer.schedule(this, 0L, this.interval);

        System.out.println("Starting... terminate with ctrl + c");

        // add shutdownhook
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("Terminating!");
            }
        });
    }

    /**
     * @brief once per interval print file size
     */
    @Override
    public void run() {
        long currentSize = this.file.length();
        if (currentSize != this.lastSize) {
            System.out.println(currentSize + " bytes");
        }
        this.lastSize = currentSize;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("no File given!");
        }
        Thread f = new Thread(new FileWatcher(args[0], 1000));
        f.start();
    }
}