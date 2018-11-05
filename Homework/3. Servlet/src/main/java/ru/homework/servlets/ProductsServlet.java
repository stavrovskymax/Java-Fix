package ru.homework.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
                        throws ServletException, IOException {
        final ArrayList<String> id = new ArrayList<String>();
        id.add("1");
        id.add("2");
        id.add("3");

        final PrintWriter printWriter = response.getWriter();
        for (int i = 0; i < id.size(); i++) {
            printWriter.println("-");
            printWriter.println(id.get(i));
            if (i == id.size()-1)
                printWriter.println("-");
        }
    }
}
