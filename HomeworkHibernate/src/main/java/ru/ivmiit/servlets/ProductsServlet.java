package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoImpl;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = new UsersDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = usersDao.findAll();
        req.setAttribute("usersFromServer", users);
        req.getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(req, resp);
    }
}
