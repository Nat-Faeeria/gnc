package gnc.dao;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import gnc.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nathanael on 22/05/17.
 */
public class ConnectionGNC {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(Constants.MYSQL_CONNECTION_PATH, Constants.MYSQL_USER, Constants.MYSQL_PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
