package dev.daonq.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if(connection == null){connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Akali", "root", "Thisisapassword");}
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null){connection.close(); connection = null;}
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        return connection.prepareStatement(sql).executeQuery();
    }
}