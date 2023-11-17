package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String dbName ="test";
    private final static String URL = "jdbc:mysql://localhost:3306/" + dbName;
    private final static String name = "root";
    private final static String password = "superSQL";
    private final static String driver = "com.mysql.jdbc.Driver";
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        return DriverManager.getConnection(URL, name, password);
    }


}
