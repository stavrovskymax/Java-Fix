package ru.ivmiit.dao;

import org.mindrot.jbcrypt.BCrypt;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private Connection connection;

    //language=SQL
    private final String SQL_SELECT_ALL_USER_WITH_CAR = "SELECT hw_user.*, hw_car.id as car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id";

    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO hw_user (firstname, lastname, login, password) " +
            "VALUES (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_MAX_USER_ID = "SELECT MAX(id) FROM hw_user";

    //language=SQL
    private final String SQL_INSERT_CAR = "INSERT INTO hw_car (owner_id, model) VALUES (?, ?)";

    //language=SQL
    private final String SQL_SELECT_USER_PASSWORD_BY_LOGIN = "SELECT password FROM hw_user WHERE login = ?";

    public UsersDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAllByFirstName(String fistName) {
        return null;
    }

    public boolean exist(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getRow() == 1) {
                    String userPassword = resultSet.getString("password");
                    return BCrypt.checkpw(password, userPassword);
                }
            }
            return false;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public User find(int id) {
        return null;
    }

    public void save(User user) {
        try {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String login = user.getLogin();
            String password = user.getPassword();
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
            List<Car> cars = user.getCars();

            Car car = cars.get(0);
            String model = car.getModel();

            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, login);
            statement.setString(4, passwordHash);
            statement.execute();
            statement = connection.prepareStatement(SQL_SELECT_MAX_USER_ID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                statement = connection.prepareStatement(SQL_INSERT_CAR);
                statement.setInt(1, id);
                statement.setString(2, model);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
                String model = resultSet.getString("model");
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
