package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.SurveyModel;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SurveyInterface {

    ArrayList<SurveyModel> getSurveyByUser(int id, String page)throws SQLException;
    ArrayList<Object> getSurveyAndQuestionsById(Integer surveyId) throws SQLException;
    Integer getSurveyId(String url) throws SQLException;
    Integer setAnswerUser(JSONObject json, Integer idsurvey) throws SQLException;
    Integer surveyClose(Integer idsurvey)throws SQLException;

}
