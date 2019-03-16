package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoImpl;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        this.usersDao = new UsersDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*List<User> users;
        if (req.getParameter("firstName") == null) {
            users = usersDao.findAll();
        } else {
            users = usersDao.findAllByFirstName(req.getParameter("firstName"));
        }*/
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
