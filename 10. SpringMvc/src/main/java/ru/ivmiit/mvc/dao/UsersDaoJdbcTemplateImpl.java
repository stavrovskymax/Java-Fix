package ru.ivmiit.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivmiit.mvc.models.Car;
import ru.ivmiit.mvc.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class UsersDaoJdbcTemplateImpl implements UsersDao {
    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_SELECT_ALL = "SELECT * FROM fix_user";


    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
            "SELECT * FROM fix_user WHERE first_name = ?";

    //language=SQL
    private final String SQL_SELECT_USER_WITH_CAR =
            "SELECT fix_user.*, fix_car.id as car_id, fix_car.model " +
                    "FROM fix_user LEFT JOIN fix_car " +
                    "ON fix_user.id = fix_car.owner WHERE fix_user.id = ?";

    //language=SQL
    private final String SQL_SELECT_USERS_WITH_CAR =
            "SELECT fix_user.*, fix_car.id as car_id, fix_car.model " +
                    "FROM fix_user LEFT JOIN fix_car " +
                    "ON fix_user.id = fix_car.owner";

    //language=SQL
    private final String SQL_SELECT_USER_BY_ID = "SELECT * FROM fix_user WHERE id = :id";

    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO fix_user(first_name, last_name) " +
            "VALUES (:firstName, :lastName)";

    @Autowired
    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private Map<Long, User> usersMap = new HashMap<>();

    private RowMapper<User> userRowMapperWithoutCar = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .build();

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        Long id = resultSet.getLong("id");

        if (!usersMap.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            User user = new User(id, firstName, lastName, new ArrayList<>());
            usersMap.put(id, user);
        }

        Car car = new Car(resultSet.getLong("car_id"), usersMap.get(id),
                resultSet.getString("model"));

        usersMap.get(id).getCars().add(car);
        return usersMap.get(id);
    };

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapperWithoutCar, firstName);
    }

    @Override
    public Optional<User> find(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<User> results = namedParameterJdbcTemplate.query(SQL_SELECT_USER_BY_ID, params,userRowMapperWithoutCar);
        if (results.isEmpty())
            return Optional.empty();
        return Optional.of(results.get(0));
    }

    @Override
    public void save(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        namedParameterJdbcTemplate.update(SQL_INSERT_USER, params);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_USERS_WITH_CAR, userRowMapper);
    }
}
