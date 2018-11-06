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

        final ArrayList<String> product = new ArrayList<String>();
        product.add("Samsung");
        product.add("Apple");
        product.add("HP");

        final ArrayList<String> cost = new ArrayList<String>();
        cost.add("200");
        cost.add("300");
        cost.add("400");

        final PrintWriter printWriter = response.getWriter();
        printWriter.print("<table>\n" +
                "  <colgroup>\n" +
                "    <col style=\"background:Khaki\">\n" +
                "    <col style=\"background:Khaki\">\n" +
                "    <col style=\"background:Khaki\">\n" +
                "  </colgroup>\n" +
                "  <tr>\n" +
                "    <th>id</th>\n" +
                "    <th>Product</th>\n" +
                "    <th>Cost, rub</th>\n" +
                "  </tr>\n");

        for (int i = 0; i < id.size(); i++) {
            printWriter.print("  <tr>\n" +
                    "    <td>" + id.get(i) + "</td>\n" +
                    "    <td>" + product.get(i) + "</td>\n" +
                    "    <td>" + cost.get(i) + "</td>\n" +
                    "  </tr>\n");
        }
    }
}
