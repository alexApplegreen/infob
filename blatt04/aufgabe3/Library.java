public class Library {

    private List items;
    // Constructor
    public Library() {
        items = new List();
    }

    public void addItem(LibraryItem item) {
        Entry e = new Entry(item);
        items.add(e);
    }

    public void deleteItem(LibraryItem item) {
        items.reset();
        while(!items.elem.getTitle().equals(item.getTitle())) {
            items.advance();
        }
        items.delete();
    }

    public LibraryItem search(String text) {
        items.reset();
        while(!items.elem.getTitle().equals(text)) {
            items.advance();
        }
        items.elem();
    }
}
