import java.sql.*;
import java.util.List;

public class KaufService {

    private Connection connection;
    private PreparedStatement preparedStatementSelect, preparedStatementInput;
    private static String URLSQL = "jdbc:sqlite:d:\\kauf.sqlite";

    public KaufService() throws SQLException {
        this.connection = DriverManager.getConnection(URLSQL);
        this.preparedStatementSelect = connection.prepareStatement("SELECT geburtsdatum, nachname, vorname, kaufdatum, goldingramm, goldimwertvoneuro FROM kauf");
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


}
