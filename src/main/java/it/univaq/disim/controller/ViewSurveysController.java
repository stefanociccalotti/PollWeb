package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.SurveyModel;
import org.apache.commons.io.FileUtils;
import org.json.*;
import org.json.CDL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ViewSurveysController")
public class ViewSurveysController extends HttpServlet {
    SurveyInterface surveyDao = new SurveyDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response,"");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String action = request.getParameter("action");
        processRequest(request,response, action);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String action){
        switch(action){
            case "viewresult":
                action_viewResult(request,response);
                break;
            case "closesurvey":
                action_closeSurvey(request,response);
                break;
            default:
                action_getViewSurvey(request,response);
        }

    }

    private void action_getViewSurvey(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        SurveyInterface surveyDao = new SurveyDao();

        try {

            ArrayList<SurveyModel> list = surveyDao.getSurveyByUser((Integer) session.getAttribute("userID"), "viewSurveys");
            request.setAttribute("list", list);
            request.setAttribute("pageCss", "./resources/dist/css/viewSurveys.css");
            request.getRequestDispatcher("jsp/viewSurveys.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_viewResult(HttpServletRequest request, HttpServletResponse response) {
        Integer idsurvey = Integer.valueOf(request.getParameter("surveyid"));
        try {
            ArrayList<String> listanswer = surveyDao.getSurveyResult(idsurvey);
            JSON2CSV(listanswer,idsurvey);
            response.sendRedirect("viewSurveys");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void action_closeSurvey(HttpServletRequest request, HttpServletResponse response) {
        Integer idsurvey = Integer.valueOf(request.getParameter("surveyid"));
        try {
            surveyDao.surveyClose(idsurvey);
            response.sendRedirect("viewSurveys");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void JSON2CSV(ArrayList<String> listanswer, Integer id){
        File file=new File("C:/Users/cakes/Desktop/answerSurvey"+id+".csv");
            try {
                FileUtils.writeStringToFile(file, String.valueOf(listanswer), Charset.defaultCharset());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
