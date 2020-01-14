package it.univaq.disim.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        /* Check if the user within the session */
        boolean loggedInUser = session.getAttribute("userID") != null;

        /* Get the login uri, to avoid infinite loop */
        String logInURI = req.getContextPath() + "/";

        /* Current reuest */
        String currentReq = req.getRequestURI();

        /* Get LoginServlet req */
        String loginServlet = req.getContextPath() + "/login";

        /* Check if current request is for loginServlet */
        boolean loginServletReq = currentReq.equals(loginServlet);

        /* Check if the request is equal to login page */
        boolean loginRequest = currentReq.equals(logInURI);

        String path = req.getRequestURI();

        if (loginRequest) {
            chain.doFilter(req, res);
        } else if (loggedInUser) {
            chain.doFilter(req, res);
        } else if (loginServletReq) {
            chain.doFilter(req, res);
        } else if(path.matches(".*(css|jpg|png|gif|js|svg|ttf|eot|woff|woff2|map)$")) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(logInURI);
        }
    }

    @Override
    public void destroy() {

    }
}
