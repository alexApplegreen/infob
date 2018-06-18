import java.io.*;
import java.util.regex.*;

public class MyReader extends BufferedReader {

    private String regex;
    private int matches;
    private int linenumber;

    /**
     * @brief constructor
     * @param isr InputStreamReader
     * @param regex String to read in read file
     * @throws IOException
     */
    public MyReader(InputStreamReader isr, String regex)  {
        super(isr);
        this.regex = regex;
        this.matches = 0;
        this.linenumber = 0;
    }

    /**
     * @brief reads a single line from file, keeps track of lineneumber
     * @return String line
     */
    public String readLine() throws IOException {
        String line = super.readLine();
        if (line != null) {
            this.linenumber++;
        }
        return line;
    }

    /**
     * @brief searches file for regex matches
     * @return String line in which regex is found
     * @throws IOException
     */
    public String search() throws IOException {
        String line;
        Pattern pattern = Pattern.compile(this.regex);
        while ((line = this.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                this.matches++;
                return line;
            }
        }
        return null;
    }

    /**
     * @brief getter for amount of matches
     * @return int
     */
    public int getAmountOfMatches() {
        return this.matches;
    }

    /**
     * @brief getter for linenumber of last read line
     * @return int
     */
    public int getLinenumber() {
        return this.linenumber;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("no regex given");
        }
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            MyReader reader = new MyReader(isr, args[0]);
            System.out.println(reader.search());
            System.out.println("Matches: " + reader.getAmountOfMatches());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}