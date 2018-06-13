public class BluRay extends LibraryItem {

    private String title;
    private String director;

    // Constructor
    public BluRay(String title, String director) {
        this.title = title;
        this.director = director;
        setBorrowed(false);
    }

    /**
     * @brief getter for field title
     * @return string
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @brief getter for field director
     * @return string
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * @brief build and return Description
     * @return string
     */
    public String getDescription() {
        return this.director + " - " + this.title;
    }
}
