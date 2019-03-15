package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Connection connection;
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = new UsersDaoImpl();
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
