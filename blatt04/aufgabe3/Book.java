public class Book extends LibraryItem {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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
     * @brief getter for field author
     * @return string
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * @brief build and return description
     * @return string
     */
    public String getDescription() {
        return this.author + " - " + this.title;
    }
}
