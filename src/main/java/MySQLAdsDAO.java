import com.mysql.cj.api.jdbc.Statement;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irby on 2/1/17.
 */
public class MySQLAdsDAO implements Ads {
private Connection connection = null;
public MySQLAdsDAO(Config config) throws RuntimeException {
    try {
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(
                config.getUrl(),
                config.getPassword(),
                config.getUsername()
        );
    } catch (SQLException e) {
        throw new RuntimeException("Error connecting to the database", e);
    }
}
@Override
    // get a list of all the ads
    public List<Ad> all() {
    Statement stmt;
    try {
        stmt = (Statement) connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ads"); // select * from ads
        return createAdsFromResults(rs);
    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving all adds", e);
    }
}

@Override
        public Long insert(Ad ad) {
    try {
        Statement stmt = (Statement) connection.createStatement();
        String sql = createInsertQuery(ad);
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    } catch (SQLException e) {
        throw new RuntimeException("Error creating a new ad.", e);
    }
}
@Override
        public Ad find(long id) {
    String sql = "SELECT * FROM ads WHERE id = " + id;

    try {
        Statement statement = (Statement) connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            return new Ad(id, title, description);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}

private String createInsertQuery(Ad ad) {
    return "INSERT INTO ads(user_id, title, description) VALUES "
            + "(" + ad.getUserId() + ", "
            + "'" + ad.getTitle() + "', "
            + "'" + ad.getDescription() + "')";
}

private Ad extractAd(ResultSet rs) throws SQLException {
    return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
    );
}
private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
    List<Ad> ads = new ArrayList<>();
    while (rs.next()) {
        ads.add(extractAd(rs));
    }
    return ads;
}
}
