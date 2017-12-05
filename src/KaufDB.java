import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KaufDB {

    private List<Kauf> kaufListeDB;

    public KaufDB() {
        this.kaufListeDB = new ArrayList<>();
    }

    public List<Kauf> getKaufListeDB() {
        return kaufListeDB;
    }

    public boolean addKauf(Kauf kauf) {
        return this.kaufListeDB.add(kauf);
    }

    public boolean addKauf(Person person, long kaufdatum, int goldInGramm) {
        return this.kaufListeDB.add(new Kauf(person, kaufdatum, goldInGramm));
    }

    public boolean addKauf(String geburtsdatum, String nachname, String vorname, long kaufDatum, int goldInGramm) {
        return this.kaufListeDB.add(new Kauf(new Person(geburtsdatum, nachname, vorname), kaufDatum, goldInGramm));
    }

    public Kauf remove(long kaufDatum) {
        Iterator<Kauf> iter = kaufListeDB.iterator();
        while (iter.hasNext()) {
            Kauf nextKauf = iter.next();
            if (nextKauf.getKaufDatum() == kaufDatum) {
                iter.remove();
                return nextKauf;
            }
        }
        return null;
    }

    public Kauf getLastItem() {
        if (this.kaufListeDB != null) {
            return this.kaufListeDB.get(this.kaufListeDB.size() - 1);
        }
        return null;
    }

    public Kauf getFirstItem() {
        if (this.kaufListeDB != null) {
            return this.kaufListeDB.get(0);
        }
        return null;
    }

    public int getSize() {
        return this.kaufListeDB.size();
    }

    public List getBetweenTimestamps(long beginTime, long endTime) {
        List<Kauf> kaufListeZeitraum = new ArrayList<>();
        for (Kauf kauf : this.kaufListeDB) {
            if (kauf.getKaufDatum() >= beginTime && kauf.getKaufDatum() <= endTime) {
                kaufListeZeitraum.add(kauf);
            }
        }
        return kaufListeZeitraum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KaufDB kaufDB = (KaufDB) o;

        return kaufListeDB != null ? kaufListeDB.equals(kaufDB.kaufListeDB) : kaufDB.kaufListeDB == null;
    }

    @Override
    public int hashCode() {
        return kaufListeDB != null ? kaufListeDB.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Kauf kauf: kaufListeDB){
            stringBuilder.append(kauf.toString() + String.format("%n"));
        }
        return stringBuilder.toString();
    }
}
