package ru.ivmiit.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Hello</h1>");
        writer.write("<h2>Max Stavrovsky</h2>");
    }
}
