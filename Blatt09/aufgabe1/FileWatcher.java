import java.io.*;
import java.lang.*;

public class FileWatcher extends Thread {

    private File file;

    public FileWatcher(String path) {
        this.file = new File(path);
        if (!this.file.exists()) {
            throw new RuntimeException("File does not exist");
        }
        System.out.println("Starting... terminate with ctrl + c");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("Terminating!");
            }
        });
    }

    @Override
    public void run() {
        while (this.file.exists()) {
            System.out.println(file.length() + " Bytes");
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("no File given!");
        }
        Thread f = new FileWatcher(args[0]);
        f.start();
    }
}