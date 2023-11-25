package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl userService = new UserServiceImpl();


        userService.createUsersTable();
        userService.saveUser("Dim", "Dava", (byte) 34);
        userService.saveUser("Dan", "Egorov", (byte) 21);
        userService.saveUser("Sam", "Lavista", (byte) 39);

        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
