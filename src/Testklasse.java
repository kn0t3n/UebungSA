import java.sql.SQLException;

public class Testklasse {

    public static void main(String[] args) {
        KaufDB kaufDB = new KaufDB();
        Kauf kauf0 = new Kauf(new Person("11.05.1985", "Schweiger", "Philipp"), 20170212, 20);
        Kauf kauf1 = new Kauf(new Person("11.05.1985", "Schweiger", "Philipp"), 20170213, 20);
        Kauf kauf2 = new Kauf(new Person("11.05.1985", "Schweiger", "Philipp"), 20170214, 20);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
