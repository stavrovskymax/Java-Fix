package ru.ivmiit.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;
import ru.ivmiit.utils.ConnectionPoolWithDataSource;

import java.util.ArrayList;
import java.util.List;


public class UsersDaoImplWithSpringJdbcTemplateTest {
    private UsersDao usersDao;

    @Before
    public void init() {
        DriverManagerDataSource dataSource = ConnectionPoolWithDataSource.getConnectionPool().getDataSource();
        usersDao = new UsersDaoImplWithSpringJdbcTemplate(dataSource);
    }

    @Test
    public void findAllByFirstName() {
        String firstName = "Max";
        List<User> users = usersDao.findAllByFirstName(firstName);

        String expexted = firstName;

        for (User user : users) {
            String actual = user.getFirstName();
            Assert.assertEquals(expexted, actual);
        }
    }

    @Test
    public void exist() {
        String login = "stavrovsky";
        String password = "qwerty";

        boolean expected = true;
        boolean actual = usersDao.exist(login, password);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveAndDelete() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String model = "car";
        String login = "ivanov1996";
        String password = "qwerty";

        User user = new User(firstName, lastName, new ArrayList<Car>(), login, password);
        Car car = new Car(user, model);
        user.getCars().add(car);

        usersDao.save(user);

        boolean actualSave = usersDao.exist(login, password);

        Assert.assertTrue(actualSave);

        usersDao.delete(user);

        boolean actualDelete = usersDao.exist(login, password);

        Assert.assertFalse(actualDelete);
    }

    @Test
    public void update() {
        String firstName = "Vladimir";
        String lastName = "Petrov";
        String model = "AURUS";
        String login = "petrov";
        String password = "qwerty";

        User user = new User(firstName, lastName, new ArrayList<Car>(), login, password);
        Car car = new Car(user, model);
        user.getCars().add(car);

        usersDao.update(user);

        String firstExpected = firstName;
        String firstActual = usersDao.findAll().get(3).getFirstName();

        Assert.assertEquals(firstExpected, firstActual);

        user.setFirstName("Ivan");

        usersDao.update(user);

        String secondExpected = "Ivan";
        String secondActual1 = usersDao.findAll().get(3).getFirstName();

        Assert.assertEquals(secondExpected, secondActual1);
    }

    @Test
    public void findAll() {
        List<User> users = usersDao.findAll();

        String expectedLoginStavrovsky = "stavrovsky";
        String expectedLoginLutohin = "lutohin";
        String expectedLoginPetrov = "petrov";

        String actualLoginStavrovsky = users.get(0).getLogin();
        String actualLoginLutohin = users.get(2).getLogin();
        String actualLoginPetrov = users.get(3).getLogin();

        Assert.assertEquals(expectedLoginStavrovsky, actualLoginStavrovsky);
        Assert.assertEquals(expectedLoginLutohin, actualLoginLutohin);
        Assert.assertEquals(expectedLoginPetrov, actualLoginPetrov);
    }
}