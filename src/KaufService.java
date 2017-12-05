import com.fasterxml.jackson.core.JsonGenerator;
import netscape.javascript.JSObject;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KaufService {

    private Connection connection;
    private PreparedStatement preparedStatementSelect, preparedStatementInput;
    private static String URLSQL = "jdbc:sqlite:d:\\kauf.sqlite";

    public KaufService() throws SQLException {
        this.connection = DriverManager.getConnection(URLSQL);
        this.preparedStatementSelect = connection.prepareStatement("SELECT geburtsdatum, nachname, vorname, kaufdatum, goldingramm, goldimwertvoneuro FROM kauf ORDER BY kaufdatum");
        this.preparedStatementInput = connection.prepareStatement("INSERT INTO kauf(geburtsdatum, nachname, vorname, kaufdatum, goldingramm, goldimwertvoneuro) VALUES (?,?,?,?,?,?)");
    }

    public void saveOneElement(Kauf kauf) throws SQLException {
        this.setPreparedInputStatement(kauf);
        preparedStatementInput.execute();
    }

    public void saveAllElements(List<Kauf> kaufDBS) throws SQLException {
        for (Kauf kauf : kaufDBS) {
            setPreparedInputStatement(kauf);
            preparedStatementInput.execute();
        }
    }

    public void setPreparedInputStatement(Kauf kauf) throws SQLException {
        preparedStatementInput.setString(1, kauf.getPerson().getGeburtsdatum());
        preparedStatementInput.setString(2, kauf.getPerson().getNachname());
        preparedStatementInput.setString(3, kauf.getPerson().getVorname());
        preparedStatementInput.setLong(4, kauf.getKaufDatum());
        preparedStatementInput.setInt(5, kauf.getGoldInGramm());
        preparedStatementInput.setDouble(6, kauf.getGoldImWertVonEuro());
    }

    public KaufDB readAllElements() throws SQLException {
        KaufDB kaufDB = new KaufDB();
        ResultSet resultSet = preparedStatementSelect.executeQuery();
        while (resultSet.next()) {
            Person person = new Person();
            Kauf kauf = new Kauf();

            person.setGeburtsdatum(resultSet.getString(1));
            person.setNachname(resultSet.getString(2));
            person.setVorname(resultSet.getString(3));
            kauf.setKaufDatum(resultSet.getLong(4));
            kauf.setGoldInGramm(resultSet.getInt(5));
            kauf.setGoldImWertVonEuro(resultSet.getDouble(6));

            kauf.setPerson(person);
            kaufDB.addKauf(kauf);
        }
        return kaufDB;
    }

    public boolean datenbankInTextFile(KaufDB kaufDB)  {

        BufferedWriter bufferedWriter = null;

        String text = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("datenbank.txt", true));
            bufferedWriter.write(kaufDB.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void textFileAuslesen(){
        BufferedReader bufferedReader = null;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader("datenbank.txt"));
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


        public String toJSON(Kauf kauf) {
            return "{ \"Geburtsdatum\": \"" + kauf.getPerson().getGeburtsdatum() + "\", \"nachname\": \"" + kauf.getPerson().getNachname()
                    + "\", \"Vorname\": \"" + kauf.getPerson().getVorname() + "\", \"kaufdatum\": \""
                    + kauf.getKaufDatum() + "\", \"goldInGramm\": \"" + kauf.getGoldInGramm()
                    + "\", \"GoldImWertVonEuro\": \"" + kauf.getGoldImWertVonEuro() + "\" }";

    }


}
