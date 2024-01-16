package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUserTable = """
                CREATE TABLE IF NOT EXISTS user(
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50),
                last_name VARCHAR(100),
                age TINYINT);""";
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().executeUpdate(createUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = """
                DROP TABLE IF EXISTS user;""";
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().executeUpdate(dropUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = """
                INSERT INTO user(name, last_name, age) VALUES (?,?,?)""";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUserById = """
                DELETE FROM user WHERE id = ?""";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(removeUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsers = """
                SELECT *
                FROM user""";
        try (Connection connection = Util.getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(getAllUsers)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong(1));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = """
                DELETE FROM user""";
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(cleanUsersTable).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
