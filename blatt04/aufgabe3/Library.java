import util.*;

public class Library {

    private List items;

    // Constructor
    public Library() {
        items = new List();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

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
