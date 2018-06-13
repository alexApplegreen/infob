import util.*;

public class Library {

    private List items;

    // Constructor
    public Library() {
        items = new List();
    }

    /**
     * @brief add item to collection
     **/
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    /**
     * @brief delete item from collection
     **/
    public void deleteItem(LibraryItem item) {
        items.reset();
        LibraryItem tmp = null;
        while (!items.endpos()) {
            tmp = (LibraryItem)items.elem();
            if (tmp.getDescription().equals(item.getDescription())) {
                items.delete();
                items.advance();
            }
            items.advance();
        }
    }

    /**
     * @brief search and return list of found items
     * @return List of LibraryItems
     **/
    public List search(String text) {
        items.reset();
        List results = new List();
        LibraryItem tmp = null;
        while(!items.endpos()) {
            tmp = (LibraryItem)items.elem();
            if (tmp.getDescription().toLowerCase().contains(text.toLowerCase())) {
                results.add(tmp);
                items.advance();
            }
            items.advance();
        }
        return results;
    }
}
