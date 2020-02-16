package it.univaq.disim.controller;

import it.univaq.disim.security.SecurityLayer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
       processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        SecurityLayer.disposeSession(request);

        try {
            response.sendRedirect("/web-engineering-pollweb/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
