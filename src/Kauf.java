public class Kauf {

    private Person person;
    private long kaufDatum;
    private int goldInGramm;
    private double goldImWertVonEuro;

    public Kauf(Person person, long kaufDatum, int goldInGramm) {
        this.person = person;
        this.kaufDatum = kaufDatum;
        this.goldInGramm = goldInGramm;
        this.goldImWertVonEuro = getGoldWertInEuro(this.goldInGramm);
    }

    public long getKaufDatum() {
        return kaufDatum;
    }

    public void setKaufDatum(long kaufDatum) {
        this.kaufDatum = kaufDatum;
    }

    public int getGoldInGramm() {
        return goldInGramm;
    }

    public void setGoldInGramm(int goldInGramm) {
        this.goldInGramm = goldInGramm;
    }

    public double getGoldImWertVonEuro() {
        return goldImWertVonEuro;
    }

    public void setGoldImWertVonEuro(double goldImWertVonEuro) {
        this.goldImWertVonEuro = goldImWertVonEuro;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private double getGoldWertInEuro(int goldInGramm){
        return goldInGramm * 34.61;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kauf kauf = (Kauf) o;

        if (kaufDatum != kauf.kaufDatum) return false;
        if (goldInGramm != kauf.goldInGramm) return false;
        if (Double.compare(kauf.goldImWertVonEuro, goldImWertVonEuro) != 0) return false;
        return person != null ? person.equals(kauf.person) : kauf.person == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = person != null ? person.hashCode() : 0;
        result = 31 * result + (int) (kaufDatum ^ (kaufDatum >>> 32));
        result = 31 * result + goldInGramm;
        temp = Double.doubleToLongBits(goldImWertVonEuro);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Kauf{" +
                "person=" + person +
                ", kaufDatum=" + kaufDatum +
                ", goldInGramm=" + goldInGramm +
                ", goldImWertVonEuro=" + goldImWertVonEuro +
                '}';
    }
}
