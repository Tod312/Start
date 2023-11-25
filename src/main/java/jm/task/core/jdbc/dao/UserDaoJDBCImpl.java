package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        final String sql = "create table if not exists User(" +
                "user_id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name varchar(50)," +
                "lastname varchar(50)," +
                "age tinyint" +
                ");";
        try (Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement())
        {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        final String sql = "drop table if exists user";
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement())
        {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String sql = "insert into User (name, lastname, age) values (?, ?, ?)";
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        final String sql = "delete from User where user_id = ?";
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        final String sql = "select * from User";
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        final String sql = "truncate table User";
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement())
        {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
