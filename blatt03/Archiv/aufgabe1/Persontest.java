public class Persontest {

    public static void main(String[] args) {

        boolean passed = true;

        Person alex = new Person("Alex");
        Person alex2 = new Person("Alex");

        Student lisa = new Student("Lisa", 123);
        Student lisa2 = new Student("Lisa", 124);

        Student alex3 = new Student("Alex", 125);

        // Hash werden nicht anhand von Schlüsselattributen erstellt
        // Systemzeit oder Speicheradresse wären sinnvoller
        if (alex.hashCode() == alex2.hashCode()) {
            passed = false;
            System.out.println("person Hashcodes collide.");
        }
        // Analog zu HashCode, Vergleich von Namen ungeeignet zur Identitätsunterscheidung
        if (alex.equals(alex2)) {
            passed = false;
            System.out.println("person equals collides");
        }
        // Sofern Matrikelnummer als Schlüsselattribut behandelt wird, keine Kollision
        // Namen zur Hashgenerierung zu nutzen nicht sinnvoll
        if(lisa.hashCode() == lisa2.hashCode()) {
            passed = false;
            System.out.println("student hashcodes collide");
        }
        // Analog zu oben
        if (lisa.equals(lisa2)) {
            passed = false;
            System.out.println("student equals collides");
        }
        // Vergleich anhand von Namen führt zu Verwechslungen
        if (alex.equals(alex3)) {
            passed = false;
            System.out.println("person equals student");
        }
        if (alex3.equals(alex)) {
            passed = false;
            System.out.println("student equals person");
        }
        if (passed) {
            System.out.println("All tests passed");
        }
        /*
        Anmerkungen:

        1.) HashCode sollte nicht mittels nicht-schlüsselattributen generiert werden
        2.) equals sollte aussagekräftige Schlüsselattribute vergleichen
        3.) equals testet nicht auf Transitivität oder Konsistenz
         */
    }
}