package gnc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nathanael on 22/05/17.
 */
public class ConnectionGNC {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(Constants.JDBC_DRIVER_CLASS_NAME).newInstance();
                connection = DriverManager.getConnection(Constants.MYSQL_CONNECTION_PATH, Constants.MYSQL_USER, Constants.MYSQL_PASSWORD);
            } catch (SQLException e) {
                System.out.println("Error while connecting to the database");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error while instanciating the connection to the database");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
