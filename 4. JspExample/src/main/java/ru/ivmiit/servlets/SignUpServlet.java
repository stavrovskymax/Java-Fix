package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.repositories.IUsersRepository;
import ru.ivmiit.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        dispatcher.forward(request, response);  // перенаправление запроса
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        LocalDate birthDay = LocalDate.parse(request.getParameter("birthday"));

        User user = new User(name, password, birthDay);
        iUsersRepository.save(user);
        doGet(request, response);
    }
}
