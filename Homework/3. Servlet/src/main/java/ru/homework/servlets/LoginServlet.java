package ru.homework.servlets;

import ru.homework.repositories.IUserRepository;
import ru.homework.repositories.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IUserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (userRepository.exist(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("name", name);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
