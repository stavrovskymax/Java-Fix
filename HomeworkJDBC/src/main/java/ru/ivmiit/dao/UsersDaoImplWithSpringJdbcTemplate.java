package ru.ivmiit.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDaoImplWithSpringJdbcTemplate implements UsersDao {

    private JdbcTemplate template;

    //language=SQL
    private final String SQL_SELECT_ALL_USER_WITH_CAR = "SELECT hw_user.*, hw_car.id as car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id";

    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO hw_user(firstname, lastname, login, password) " +
            "VALUES (?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_MAX_USER_ID = "SELECT MAX(id) AS max_id FROM hw_user";

    //language=SQL
    private final String SQL_INSERT_CAR = "INSERT INTO hw_car(owner_id, model) VALUES (?, ?)";

    //language=SQL
    private final String SQL_SELECT_USER_PASSWORD_BY_LOGIN = "SELECT password FROM hw_user WHERE login = ?";

    //language=SQL
    private final String SQL_SELECT_USER_BY_FIRST_NAME = "SELECT hw_user.*, hw_car.id AS car_id, hw_car.model " +
            "FROM hw_user LEFT JOIN hw_car ON hw_user.id = hw_car.owner_id WHERE firstname = ?";

    //language=SQL
    private final String SQL_UPDATE_USER = "UPDATE hw_user SET firstname = ?, lastname = ? WHERE login = ?";

    //language=SQL
    private final String SQL_DELETE_CAR = "DELETE FROM hw_car WHERE owner_id = (SELECT id FROM hw_user WHERE login = ?);";

    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM hw_user WHERE login = ?";

    public UsersDaoImplWithSpringJdbcTemplate(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        Integer carId = rs.getInt("car_id");
        String model = rs.getString("model");

        User user = new User(id, firstName, lastName, new ArrayList<Car>());
        Car car = new Car(carId, user, model);
        user.getCars().add(car);

        return user;
    };

    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_USER_BY_FIRST_NAME, userRowMapper, firstName);
    }

    public boolean exist(String login, String password) {
        List<String> userPasswords = template.query(SQL_SELECT_USER_PASSWORD_BY_LOGIN, (rs, rowNum) ->
                rs.getString("password"), login);
        if (!userPasswords.isEmpty()) {
            String userPassword = userPasswords.get(0);
            return BCrypt.checkpw(password, userPassword);
        }
        return false;
    }

    public void save(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String login = user.getLogin();
        String password = user.getPassword();
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(10));

        List<Car> cars = user.getCars();
        Car car = cars.get(0);
        String model = car.getModel();

        template.update(SQL_INSERT_USER, firstName, lastName, login, passwordHash);
        List<Integer> carsId = template.query(SQL_SELECT_MAX_USER_ID, (rs, rowNum) -> rs.getInt("max_id"));
        Integer carId = carsId.get(0);
        template.update(SQL_INSERT_CAR, carId, model);
    }

    public void update(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String login = user.getLogin();
        template.update(SQL_UPDATE_USER, firstName, lastName, login);
    }

    public void delete(User user) {
        String login = user.getLogin();
        template.update(SQL_DELETE_CAR, login);
        template.update(SQL_DELETE_USER, login);
    }

    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL_USER_WITH_CAR, userRowMapper);
    }
}
