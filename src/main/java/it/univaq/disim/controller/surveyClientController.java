package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.QuestionModel;
import it.univaq.disim.model.SurveyModel;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
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
import java.util.Enumeration;

@WebServlet(name = "surveyClientController")
public class surveyClientController extends HttpServlet {
    SurveyInterface surveyDao = new SurveyDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surveyURL = request.getParameter("url");
        HttpSession session=request.getSession();
        String logCheck = (String) session.getAttribute("client");

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
                    Integer idsurv = surveyDao.getSurveyId(surveyURL);
                    ArrayList<Object> totalsurvey = surveyDao.getSurveyAndQuestionsById(idsurv);

                    request.setAttribute("surveyid",idsurv);
                    request.setAttribute("survey", totalsurvey.get(0));//TODO: se fullSurvey.get(0) == null => redirect su pagina di errore oppure messaggio di errore?
                    request.setAttribute("questions",totalsurvey.get(1));
                    request.setAttribute("numberOfQuestions", totalsurvey.get(2));
                    request.setAttribute("code",totalsurvey.get(3));//TODO: vedere se code serve e terminare la modifica aggiungendo error.jsp
                    request.setAttribute("pageCss", "./resources/dist/css/viewSurvey.css");
                    request.setAttribute("pageJs","./resources/dist/js/pages/view-survey/view-survey.js");
                    request.setAttribute("printAll",printFullSurvey(totalsurvey));

                    request.getRequestDispatcher("jsp/surveyClient.jsp").forward(request, response);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            //request.getRequestDispatcher("jsp/surveyClient.jsp").forward(request, response);
        }

    private String printFullSurvey(ArrayList<Object> fullSurvey) {

        String surveyP = "";
        String questionsP = "";
        SurveyModel surveyInfo = (SurveyModel) fullSurvey.get(0);
        ArrayList<QuestionModel> questionList = (ArrayList<QuestionModel>) fullSurvey.get(1);
        Integer codeP = (Integer) fullSurvey.get(3);

        if(surveyInfo != null) {
            surveyP = "Survey info: " + fullSurvey.get(0).toString();
        }
        if(questionList != null) {
            for (QuestionModel item : questionList) {
                questionsP += "_&_Question: " + item.toString();
            }
        }

        return surveyP + questionsP + "_&_Code = " + codeP;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        Enumeration<String> parameterNames = request.getParameterNames();

        JSONObject jsonObj = new JSONObject(parameterNames);

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            System.out.println(paramName + ": ");

            String[] paramValues = request.getParameterValues(paramName);
            jsonObj.put(paramName, paramValues);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println(paramValue);
            }
        }
        System.out.println(jsonObj);
        //mando tutto al db
        try {
            surveyDao.setAnswerUser(jsonObj, Integer.valueOf(request.getParameter("idans")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
