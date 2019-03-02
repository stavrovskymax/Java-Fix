package ru.ivmiit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImplWithSpringJdbcTemplate implements UsersDao {

    private JdbcTemplate template;

    public UserDaoImplWithSpringJdbcTemplate(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private Map<Integer, User> userMap = new HashMap<>();

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        Integer id = resultSet.getInt("id");

        if (!userMap.containsKey(id)) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            User user = new User(firstName, lastName, new ArrayList<>(), login, password);
            userMap.put(id, user);
        }

        Integer idCar = resultSet.getInt("id");
        String modelCar = resultSet.getString("model");

        Car car = new Car(idCar, userMap.get(id), modelCar);
        userMap.get(id).getCars().add(car);

        return userMap.get(id);
    };

    public List<User> findAllByFirstName(String fistName) {
        return null;
    }

    public boolean exist(String login, String password) {
        return false;
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
        return null;
    }
}
