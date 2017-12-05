public class Person {

    private String geburtsdatum, nachname, vorname;

    public Person() {
    }

    public Person(String geburtsdatum, String nachname, String vorname) {
        this.geburtsdatum = geburtsdatum;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (geburtsdatum != null ? !geburtsdatum.equals(person.geburtsdatum) : person.geburtsdatum != null)
            return false;
        if (nachname != null ? !nachname.equals(person.nachname) : person.nachname != null) return false;
        return vorname != null ? vorname.equals(person.vorname) : person.vorname == null;
    }

    @Override
    public int hashCode() {
        int result = geburtsdatum != null ? geburtsdatum.hashCode() : 0;
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GebDatum: " + geburtsdatum + " Nachname: " + nachname +" Vorname: " + vorname + String.format("%n"));
        return stringBuilder.toString();
//        return "Person{" +
//                "geburtsdatum='" + geburtsdatum + '\'' +
//                ", nachname='" + nachname + '\'' +
//                ", vorname='" + vorname + '\'' +
//                '}';
    }
}
