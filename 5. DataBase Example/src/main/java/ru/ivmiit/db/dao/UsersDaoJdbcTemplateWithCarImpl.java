package ru.ivmiit.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.db.model.Car;
import ru.ivmiit.db.model.User;

import javax.sql.DataSource;
import java.util.*;

public class UsersDaoJdbcTemplateWithCarImpl implements UsersDao {
    private JdbcTemplate template;

    private final String SQL_SELECT_ALL = "SELECT * FROM fix_user";

    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
            "SELECT * FROM fix_user WHERE first_name = ?";

    //language=SQL
    private final String SQL_SELECT_USER_WITH_CAR =
            "SELECT fix_user.*, fix_car.id as car_id, fix_car.model " +
                    "FROM fix_user LEFT JOIN fix_car " +
                    "ON fix_user.id = fix_car.owner_id WHERE fix_user.id = ?";

    public UsersDaoJdbcTemplateWithCarImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private Map<Integer, User> usersMap = new HashMap<>();

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        Integer id = resultSet.getInt("id");

        if (!usersMap.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            User user = new User(id, firstName, lastName,new ArrayList<>());
            usersMap.put(id, user);
        }

        Car car = new Car(resultSet.getInt("car_id"),
                resultSet.getString("model"), usersMap.get(id));

        usersMap.get(id).getCars().add(car);
        return usersMap.get(id);
    };

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapper, firstName);
    }

    @Override
    public Optional<User> find(Integer id) {
        template.query(SQL_SELECT_USER_WITH_CAR, userRowMapper, id);

        if (usersMap.containsKey(id))
            return Optional.of(usersMap.get(id));
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }
}
