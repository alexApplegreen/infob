import java.io.*;

public class MyReader extends BufferedReader {

    private String regex;
    private File handle;
    private int matches;

    /**
     * @brief constructor
     * @param handle file to read from
     * @param regex String to read in read file
     * @throws IOException
     */
    public MyReader(FileReader fr, String regex)  {
        super(fr);
        this.regex = regex;
        this.matches = 0;
    }

    /**
     * @brief reads a single line from file
     * @return String line
     */
    public String readLine() throws IOException {
        return super.readLine();
    }

    public int getAmountOfMatches() {
        return this.matches;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Keine Datei angegeben");
        }
        File file = new File(args[0]);
        try {
            FileReader fr = new FileReader(file);
            MyReader reader = new MyReader(fr, null);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not foun!");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}