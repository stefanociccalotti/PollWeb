package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.SurveyModel;
import it.univaq.disim.model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "loginController")
public class loginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        UserInterface dao = new UserDao();
        Integer result = dao.loginQuery(user,pass);

        if(result != null){
            HttpSession session=request.getSession();
            session.setAttribute("userID",result);
            session.setAttribute("page","frame.jsp");
        }

        //RequestDispatcher rd = request.getRequestDispatcher("jsp/frame.jsp");
        //rd.forward(request,response);

        response.sendRedirect("jsp/home.jsp");

    }
}
