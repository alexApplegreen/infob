import java.io.*;
import java.lang.Thread.*;

public class FileWatcher extends Thread {

    private File file;

    public FileWatcher(String path) throws IOException {
        this.file = new File(path);
        if (!this.file.exists()) {
            throw new IOException("File does not exist");
        }
        Runtime.getRuntime.addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("Terminating!");
            }
        });

    }

    @Override
    public void run() {
        System.out.println(file.getTotalSpace());
        try {
            this.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("no File given!");
        }
        try {
            FileWatcher f = new FileWatcher(args[0]);
            f.run();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }
}