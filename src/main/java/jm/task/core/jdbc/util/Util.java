package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String MYSQL_URL = "jdbc:mysql://localhost:3306/test";
    private final static String POSTGRES_URL  ="jdbc:postgresql://localhost:5433/Test";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "superSQL";
    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String POSTGRES_DRIVER = "org.postgresql.Driver";

    private  static final SessionFactory SESSION_FACTORY;

    static {

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", POSTGRES_DRIVER);
        configuration.setProperty("hibernate.connection.url", POSTGRES_URL);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.pool_size", "1");
        configuration.addAnnotatedClass(User.class);
        SESSION_FACTORY = configuration.buildSessionFactory();
    }

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);
        return DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
    }

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }



}
