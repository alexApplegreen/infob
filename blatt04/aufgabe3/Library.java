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
        boolean found = false;
        LibraryItem tmp = null;
        items.reset();
        while(!found) {
            System.out.println("Schleifeneintritt");
            tmp = (LibraryItem)items.elem();
            if (tmp.getDescription().equals(item.getDescription())) {
                items.delete();
                found = true;
            }
            try {
                items.advance();
                System.out.println("advancing");
            }
            catch (RuntimeException e) {
                System.out.println("Item not found");
            }
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
        }
        return results;
    }
}
