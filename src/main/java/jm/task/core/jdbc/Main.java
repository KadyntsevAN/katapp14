package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ivan", "Petrov", (byte) 42);
        userDaoJDBC.saveUser("Petr", "Sidorov", (byte) 34);
        userDaoJDBC.saveUser("Olga", "Popova", (byte) 51);
        userDaoJDBC.saveUser("Vladimir", "Smirnov", (byte) 23);
        List<User> list = userDaoJDBC.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
