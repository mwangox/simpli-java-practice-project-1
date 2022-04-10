package tz.co.simplilearn.product_details.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionUtils {

    public  static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionString = String.format("jdbc:mysql://%s:%s/%s", DatabaseUtils.DATABASE_HOST,
                DatabaseUtils.DATABASE_PORT,
                DatabaseUtils.DATABASE_DB_NAME);
        return DriverManager.getConnection(connectionString, DatabaseUtils.DATABASE_USERNAME, DatabaseUtils.DATABASE_PASSWORD );
    }

}
