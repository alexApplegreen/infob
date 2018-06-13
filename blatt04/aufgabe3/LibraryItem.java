public abstract class LibraryItem {

    private boolean isBorrowed;

    /**
     * @brief getter for field isBorrowed
     * @return boolean
     */
    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    /**
     * @brief setter for field isBorrowed
     * @param isBorrowed boolean
     */
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    /**
     * @brief build and return Description
     * @return string
     */
    public abstract String getDescription();
}
