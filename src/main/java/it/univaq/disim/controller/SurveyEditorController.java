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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SurveyEditorController")
public class SurveyEditorController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SurveyInterface surveyDao = new SurveyDao();
        String surveyId = request.getParameter("survey");

        if (surveyId == null) {

            request.setAttribute("pageCss", "./resources/dist/css/surveyEditor.css");
            request.setAttribute("numberOfQuestions", 0);
            request.getRequestDispatcher("jsp/surveyEditor.jsp").forward(request, response);

        } else {

            try {

                ArrayList<Object> fullSurvey = surveyDao.getSurveyAndQuestionsById( Integer.parseInt(surveyId) );

                request.setAttribute("survey", fullSurvey.get(0));//TODO: se fullSurvey.get(0) == null => redirect su pagina di errore oppure messaggio di errore?
                request.setAttribute("questions",fullSurvey.get(1));
                request.setAttribute("numberOfQuestions", fullSurvey.get(2));
                request.setAttribute("code",fullSurvey.get(3));//TODO: vedere se code serve e terminare la modifica aggiungendo error.jsp
                request.setAttribute("pageCss", "./resources/dist/css/surveyEditor.css");
                request.setAttribute("pageJs","./resources/dist/js/pages/surveyEditor/surveyEditor.js");
                request.setAttribute("printAll",printFullSurvey(fullSurvey));

                request.getRequestDispatcher("jsp/surveyEditor.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

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

}

/*request.setAttribute("printAll",printFullSurvey(fullSurvey));
<script type="text/javascript">
    const array = "${printAll}".split("_&_");
    array.forEach(element => console.log(element));
</script>*/