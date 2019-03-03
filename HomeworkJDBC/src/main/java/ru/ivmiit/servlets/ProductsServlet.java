package ru.ivmiit.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoImpl;
import ru.ivmiit.dao.UsersDaoImplWithSpringJdbcTemplate;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUrl(dbUrl);
            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setDriverClassName(driverClassName);

            this.usersDao = new UsersDaoImplWithSpringJdbcTemplate(dataSource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = usersDao.findAll();
        req.setAttribute("usersFromServer", users);
        req.getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String model = req.getParameter("model");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (!usersDao.exist(login, password)) {
            User user = new User(firstName, lastName, new ArrayList<Car>(), login, password);
            Car car = new Car(user, model);
            user.getCars().add(car);
            usersDao.save(user);
        } else {
            req.setAttribute("errorLoginExists", "Login already exists");
        }
        doGet(req, resp);
    }
}
