import java.util.Comparator;

public class Sortieren implements Comparator<Kauf>{


    @Override
    public int compare(Kauf o1, Kauf o2) {
        return o1.getGoldInGramm() - o2.getGoldInGramm();
    }


}
