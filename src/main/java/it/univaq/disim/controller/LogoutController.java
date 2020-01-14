package it.univaq.disim.controller;

import it.univaq.disim.security.SecurityLayer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SecurityLayer.disposeSession(request);

        response.sendRedirect("/web-engineering-pollweb/");

    }
}
