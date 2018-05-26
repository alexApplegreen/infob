package genericList;

// Erbt nicht, weil eh nur Attribute und Konstruktoren vorhanden sind, die geändert werden müssen

class GenericEntry<T>{

    T o;
    GenericEntry next;

    GenericEntry() {
        this(null, null);
    }

    GenericEntry(T o) {
        this(o, null);
    }

    GenericEntry(T o, GenericEntry e) {
        this.o = o;
        this.next = e;
    }
}
