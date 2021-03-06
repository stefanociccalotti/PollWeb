package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.SurveyModel;
import it.univaq.disim.model.QuestionModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SurveyEditorController")
public class SurveyEditorController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        processRequest(request,response,"");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response,"updatesurvey");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String action){
        switch(action){
            case "updatesurvey":
                action_updateSurvey(request,response);
                break;
            default:
                action_getSurveyEditor(request,response);
        }

    }

    private void action_getSurveyEditor(HttpServletRequest request, HttpServletResponse response) {
        SurveyInterface surveyDao = new SurveyDao();
        String surveyId = request.getParameter("survey");

        if (surveyId == null ) {

            try {
                request.setAttribute("pageCss", "./resources/dist/css/surveyEditor.css");
                request.setAttribute("pageJs","./resources/dist/js/pages/surveyEditor/surveyEditor.js");
                request.getRequestDispatcher("jsp/surveyEditor.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            try {

                ArrayList<Object> fullSurvey = surveyDao.getSurveyAndQuestionsById( Integer.parseInt(surveyId) );

                request.setAttribute("survey", fullSurvey.get(0));
                request.setAttribute("questions",fullSurvey.get(1));
                request.setAttribute("numberOfQuestions", fullSurvey.get(2));
                request.setAttribute("code",fullSurvey.get(3));
                request.setAttribute("pageCss", "./resources/dist/css/surveyEditor.css");
                request.setAttribute("pageJs","./resources/dist/js/pages/surveyEditor/surveyEditor.js");

                request.getRequestDispatcher("jsp/surveyEditor.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void action_updateSurvey(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        SurveyInterface surveyDao = new SurveyDao();
        HttpSession s = request.getSession(true);
        Integer userID = (Integer) s.getAttribute("userID");

        try {
            Integer statusCode = surveyDao.submitSurveyAndQuestions(data,userID);
            PrintWriter out = null;
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(statusCode);

            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(out != null && statusCode == 200) out.write("Richiesta eseguita con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}