package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.repositories.IUsersRepository;
import ru.ivmiit.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private IUsersRepository iUsersRepository;

    @Override
    public void init() throws ServletException {
        this.iUsersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        List<User> users = iUsersRepository.findAll();
    }
}
