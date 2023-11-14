package bot.discord;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:/home/maurian/Documents/JAVA/footballers.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL);
    }
}
