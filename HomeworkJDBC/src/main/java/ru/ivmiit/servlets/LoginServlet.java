package ru.ivmiit.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoImpl;
import ru.ivmiit.dao.UsersDaoImplWithSpringJdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        this.usersDao = new UsersDaoImplWithSpringJdbcTemplate(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (usersDao.exist(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("errorLoginOrPasswordIncorrect", "Login or password is incorrect");
            req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
