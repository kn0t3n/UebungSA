import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        preparedStatementInput.setString(1, kauf.getPerson().getGeburtsdatum());
        preparedStatementInput.setString(2, kauf.getPerson().getNachname());
        preparedStatementInput.setString(3, kauf.getPerson().getVorname());
        preparedStatementInput.setLong(4, kauf.getKaufDatum());
        preparedStatementInput.setInt(5, kauf.getGoldInGramm());
        preparedStatementInput.setDouble(6, kauf.getGoldImWertVonEuro());
        preparedStatementInput.execute();
    }

    public void saveAllElements(List<Kauf> kaufDBS) throws SQLException {
        for (Kauf kauf :kaufDBS) {
            preparedStatementInput.setString(1, kauf.getPerson().getGeburtsdatum());
            preparedStatementInput.setString(2, kauf.getPerson().getNachname());
            preparedStatementInput.setString(3, kauf.getPerson().getVorname());
            preparedStatementInput.setLong(4, kauf.getKaufDatum());
            preparedStatementInput.setInt(5, kauf.getGoldInGramm());
            preparedStatementInput.setDouble(6, kauf.getGoldImWertVonEuro());
            preparedStatementInput.execute();
        }
    }


}
