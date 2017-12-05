import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Testklasse {

    public static void main(String[] args) {
        KaufDB kaufDB = new KaufDB();
        Kauf kauf0 = new Kauf(new Person("11.05.1985", "Schweiger", "Philipp"), 20170214, 20);
        Kauf kauf1 = new Kauf(new Person("11.05.1986", "Blubb", "Philipp"), 20170213, 20);
        Kauf kauf2 = new Kauf(new Person("11.05.1987", "Muh", "Philipp"), 20170211, 20);
        System.out.println(kauf0);
        kaufDB.addKauf(kauf0);
        kaufDB.addKauf(kauf1);
        kaufDB.addKauf(kauf2);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(kaufDB);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(kaufDB.getBetweenTimestamps(1, 20170213));

        KaufService kaufService = null;
        try {
            kaufService = new KaufService();
            kaufService.saveAllElements(kaufDB.getKaufListeDB());
            System.out.println(kaufService.readAllElements());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Kauf> arr = new ArrayList<>();
        arr.add(kauf0);
        arr.add(kauf1);
        arr.add(kauf2);


        Collections.sort(arr, new Sortieren());
        System.out.println(arr);

        kaufService.datenbankInTextFile(kaufDB);


        System.out.println("-------------------------------------------------------------------");

        kaufService.textFileAuslesen();


    }

}
