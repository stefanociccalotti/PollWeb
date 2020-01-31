package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.SurveyModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "surveyClientController")
public class surveyClientController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SurveyInterface surveyDao = new SurveyDao();
        String surveyURL = request.getParameter("url");
        HttpSession session=request.getSession();
        String logCheck = (String) session.getAttribute("client");

        System.out.println(surveyURL);
        System.out.println(logCheck);

            try {
                Integer surveyId = surveyDao.getSurveyId(surveyURL);
                ArrayList<Object> fullSurvey = surveyDao.getSurveyAndQuestionsById(surveyId);

                //cosi prendo il valore pubblico/privato per verificare se è necessario il login o meno
                SurveyModel s = (SurveyModel) fullSurvey.get(0);

                if(s.getPrivacy().equals("riservato") && logCheck == null){
                    //il sondaggio è privato quindi mando l'utente alla pagina di login
                    request.setAttribute("url",surveyURL);
                    request.getRequestDispatcher("login").forward(request, response);
                    //response.sendRedirect("loginclient?url="+surveyURL);

                }else{
                    request.getRequestDispatcher("jsp/surveyClient.jsp").forward(request, response);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            //request.getRequestDispatcher("jsp/surveyClient.jsp").forward(request, response);
        }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
