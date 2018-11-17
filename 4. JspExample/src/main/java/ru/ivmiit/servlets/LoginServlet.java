package ru.ivmiit.servlets;

import ru.ivmiit.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsersRepositoryInMemoryImpl usersRepositoryInMemory;

    @Override
    public void init() throws ServletException {
        this.usersRepositoryInMemory = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String password = req.getParameter("password");

        if (usersRepositoryInMemory.exist(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);
            req.getServletContext().getRequestDispatcher("/home").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
