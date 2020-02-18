package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.SurveyModel;
import it.univaq.disim.security.SecurityLayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "homeController")
public class HomeController extends HttpServlet {
    SurveyInterface surveydao = new SurveyDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        try {
            ArrayList<SurveyModel> list = surveydao.getSurveyByUser((Integer) session.getAttribute("userID"), "home");
            request.setAttribute("list",list);
            request.setAttribute("pageCss","./resources/dist/css/home.css");
            request.getRequestDispatcher("jsp/home.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
            list.clear();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
