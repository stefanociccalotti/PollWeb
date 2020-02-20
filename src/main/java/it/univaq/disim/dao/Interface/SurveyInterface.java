package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.QuestionModel;
import it.univaq.disim.model.SurveyModel;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public interface SurveyInterface {

    ArrayList<SurveyModel> getSurveyByUser(int id, String page)throws SQLException;
    ArrayList<Object> getSurveyAndQuestionsById(Integer surveyId) throws SQLException;
    Integer getSurveyId(String url) throws SQLException;
    Integer setAnswerUser(TreeMap<String,String> answern, Integer idsurvey) throws SQLException;
    Integer surveyClose(Integer idsurvey)throws SQLException;
    Integer submitSurveyAndQuestions(String data, Integer id) throws SQLException;
    ArrayList<QuestionModel> getSurveyResult(Integer idsurvey)throws SQLException;
    Integer publishSurvey(Integer idsurvey) throws SQLException;

}
