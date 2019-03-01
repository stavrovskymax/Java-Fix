package ru.ivmiit.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.db.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements UsersDao {

    private JdbcTemplate template;

    private final String SQL_SELECT_ALL = "SELECT * FROM fix_user";

    private final String SQL_SELECT_ALL_BY_FIRST_NAME = "SELECT * FROM fix_user WHERE first_name = ?";

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, rowNum) ->
            new User(
            resultSet.getInt("id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"));

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapper, firstName);
    }

    @Override
    public Optional<User> find(Integer id) {
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
