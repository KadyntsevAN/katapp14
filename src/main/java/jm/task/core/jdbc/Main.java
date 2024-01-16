package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 42);
        userService.saveUser("Petr", "Sidorov", (byte) 34);
        userService.saveUser("Olga", "Popova", (byte) 51);
        userService.saveUser("Vladimir", "Smirnov", (byte) 23);
        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
