package ru.ivmiit.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDaoImplTest {

    @Test
    public void findAllByFirstName() {
        UsersDao usersDao = new UsersDaoImpl();

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
        UsersDao usersDao = new UsersDaoImpl();

        String login = "stavrovsky";
        String password = "qwerty";

        boolean expected = true;
        boolean actual = usersDao.exist(login, password);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveAndDelete() {
        UsersDao usersDao = new UsersDaoImpl();

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
        UsersDao usersDao = new UsersDaoImpl();

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
        UsersDao usersDao = new UsersDaoImpl();

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