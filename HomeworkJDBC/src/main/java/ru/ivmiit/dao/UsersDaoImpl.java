package ru.ivmiit.dao;

import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private Connection connection;

    private final String SQL_SELECT_ALL_USER = "SELECT * FROM hw_user";

    //language=SQL
    private final String SQL_SELECT_ALL_USER_WITH_CAR = "SELECT hw_user.*, hw_car.id as car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id";

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
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USER_WITH_CAR);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Integer car_id = resultSet.getInt("car_id");
                String  model = resultSet.getString("model");

                User user = new User(id, firstName, lastName, new ArrayList<Car>());
                Car car = new Car(car_id, user, model);
                user.getCars().add(car);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
