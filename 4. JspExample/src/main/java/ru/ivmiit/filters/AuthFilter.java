package ru.ivmiit.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/home")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest)request;
        final HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.getServletContext().getRequestDispatcher("/login").forward(req, resp);
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
