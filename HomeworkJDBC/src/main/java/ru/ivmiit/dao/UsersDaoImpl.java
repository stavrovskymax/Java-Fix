package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private Connection connection;
    private final String SQL_SELECT_ALL_USERS = "SELECT * FROM hw_user";

    public UsersDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAllByFirstName(String fistName) {
        return null;
    }

    public User find(int id) {
        return null;
    }

    public void save(User model) {

    }

    public void update(User model) {

    }

    public void delete(User model) {

    }

    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<User>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                User user = new User(id, firstName, lastName);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
