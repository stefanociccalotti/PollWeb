package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginController")
public class loginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        System.out.println(user);

        UserInterface dao = new UserDao();
        Boolean result = dao.loginQuery(user,pass);

        if(result){
            request.setAttribute("user", user);
        }

        RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
        rd.forward(request,response);

    }
}
