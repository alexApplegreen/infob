import java.io.*;

public class MyReader {

    private String regex;
    private File handle;
    private int lineNumber;
    private int matches;

    /**
     * @brief constructor
     * @param handle file to read from
     * @param regex String to read in read file
     * @throws IOException
     */
    public MyReader(String handle, String regex)  {
        this.handle = new File(handle);
        this.regex = regex;
        this.lineNumber = 0;
        this.matches = 0;
    }

    /**
     * @brief reads a single line from file
     * @return String line
     */
    public String readLine() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(handle))) {
            if (this.lineNumber != 0) {
                while ((br.read() != 10)) {
                    br.skip(1);
                }
            }
            if ((line = br.readLine()) != null) {
                this.lineNumber++;
                if (this.regex != null) {
                    if (line.contains(this.regex)) {
                        this.matches++;
                    }
                }
                return line;
            }
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getAmountOfMatches() {
        return this.matches;
    }

    public static void main(String[] args) {
        MyReader reader = new MyReader(args[0], null);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(reader.lineNumber);
    }
}