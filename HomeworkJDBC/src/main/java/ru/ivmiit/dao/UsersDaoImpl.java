package ru.ivmiit.dao;

import org.mindrot.jbcrypt.BCrypt;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;
import ru.ivmiit.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private Connection connection;

    //language=SQL
    private final String SQL_SELECT_ALL_USER_WITH_CAR = "SELECT hw_user.*, hw_car.id as car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id";

    //language=SQL
    private final String SQL_SELECT_USER_WITH_CAR_BY_FIRST_NAME = "SELECT hw_user.*, hw_car.id as car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id WHERE hw_user.firstname = ?";

    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO hw_user (firstname, lastname, login, password) " +
            "VALUES (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_MAX_USER_ID = "SELECT MAX(id) FROM hw_user";

    //language=SQL
    private final String SQL_INSERT_CAR = "INSERT INTO hw_car (owner_id, model) VALUES (?, ?)";

    //language=SQL
    private final String SQL_SELECT_USER_PASSWORD_BY_LOGIN = "SELECT password FROM hw_user WHERE login = ?";

    //language=SQL
    private final String SQL_DELETE_CAR_BY_LOGIN = "DELETE FROM hw_car WHERE owner_id = (SELECT id FROM hw_user WHERE login = ?);";

    //language=SQL
    private final String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM hw_user WHERE login = ?";

    //language=SQL
    private final String SQL_UPDATE_FIRST_NAME_BY_LOGIN = "UPDATE hw_user SET firstName = ? WHERE login = ?";

    public UsersDaoImpl() {
        ConnectionPool data = ConnectionPool.getConnectionPool();
        try {
            String dbUrl = data.getProperty("db.url");
            String dbUsername = data.getProperty("db.username");
            String dbPassword = data.getProperty("db.password");
            String driverClassName = data.getProperty("db.driverClassName");

            Class.forName(driverClassName);
            this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<User> findAllByFirstName(String userFirstName) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_WITH_CAR_BY_FIRST_NAME);
            statement.setString(1, userFirstName);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                user = new User(firstName, lastName, new ArrayList<Car>(), login, password);
                String model = resultSet.getString("model");
                Car car = new Car(user, model);
                user.getCars().add(car);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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

    public void update(User user) {
        try {
            String firstName = user.getFirstName();
            String login = user.getLogin();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FIRST_NAME_BY_LOGIN);
            statement.setString(1, firstName);
            statement.setString(2, login);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(User user) {
        try {
            String login = user.getLogin();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CAR_BY_LOGIN);
            statement.setString(1, login);
            statement.execute();
            statement = connection.prepareStatement(SQL_DELETE_USER_BY_LOGIN);
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Integer carId = resultSet.getInt("car_id");
                String model = resultSet.getString("model");

                User user = new User(id, firstName, lastName, new ArrayList<Car>(), login, password);
                Car car = new Car(carId, user, model);
                user.getCars().add(car);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
