package it.univaq.disim.dao;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.model.QuestionModel;
import it.univaq.disim.model.SurveyModel;

import java.sql.*;
import java.util.ArrayList;
import javax.json.*;
import java.io.StringReader;

public class SurveyDao implements SurveyInterface {

    private ArrayList<SurveyModel> listsurvey = new ArrayList<>();

    @Override
    public ArrayList<SurveyModel> getSurveyByUser(int id, String page) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        String sql ="{CALL spSurvey_getByUser(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {

            listsurvey.add(new SurveyModel(rs.getInt("id"),rs.getString("url"),rs.getString("privacy"),rs.getString("status"),
                    rs.getString("title"),rs.getString("opening"),rs.getString("closing"),rs.getInt("user"),page));

        }

        return listsurvey;

    }

    @Override
    public ArrayList<Object> getSurveyAndQuestionsById(Integer surveyId) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        String sql = "{CALL pollweb.spQuestion_getBySurvey(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1,surveyId);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Object> fullSurvey = new ArrayList<>();
        ArrayList<QuestionModel> questionsList = new ArrayList<>();
        Integer numberOfQuestions = 0;

        rs.next();

        Integer code = rs.getInt("code");

        switch(code) {
            case 2:
                //CODICE 2: SONDAGGIO ESISTE E CI SONO DOMANDE. INOLTRE SALVO IL SONDAGGIO SOLO QUANDO CICLO SULLA PRIMA ROW.
                fullSurvey.add(getSurvey(rs));
                questionsList.add(getQuestion(rs));
                numberOfQuestions++;
                //NELLE ALTRE ROWS SI DEVONO SALVARE SOLO LE INFORMAZIONI DELLE DOMANDE (SE CI SONO)
                while(rs.next()) {
                    questionsList.add(getQuestion(rs));
                    numberOfQuestions++;
                }
                fullSurvey.add(questionsList);
                break;
            case 1:
                //CODICE 1: IL SONDAGGIO ESISTE MA NON CI SONO DOMANDE(AGGIUNGO null PER FAR COMBACIARE GLI INDICI IN SurveyEditorController).
                fullSurvey.add(getSurvey(rs));
                fullSurvey.add(null);
                break;
            case 0:
                //CODICE 0: IL SONDAGGIO NON ESISTE E NON CI SONO DOMANDE.
                fullSurvey.add(null);
                fullSurvey.add(null);
                break;
        }

        fullSurvey.add(numberOfQuestions);
        fullSurvey.add(code);

        return fullSurvey;

    }


    private SurveyModel getSurvey(ResultSet rs) throws SQLException {

        return new SurveyModel(rs.getInt("surveyid"),rs.getString("url"),rs.getString("privacy"),rs.getString("status"),
                rs.getString("title"),rs.getString("opening"),rs.getString("closing"),rs.getInt("user"),"surveyEditor");

    }

    private QuestionModel getQuestion(ResultSet rs) throws SQLException {

        JsonObject jsonObj = jsonFromString(rs.getString("text"));
        String type = jsonObj.getString("type");
        String[] answers = getAnswersFromJsonArray(jsonObj, type);

        return new QuestionModel(rs.getInt("questionId"),rs.getInt("mandatory"),rs.getInt("number")
                ,type,answers,jsonObj.getString("question"),rs.getString("note"),rs.getInt("surveyId"));

    }


    private static JsonObject jsonFromString(String jsonStr) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonStr));
        JsonObject jsonObj = jsonReader.readObject();
        jsonReader.close();

        return jsonObj;

    }

    private String[] getAnswersFromJsonArray(JsonObject jsonObj, String type) {

        if(type.equals("singAns") || type.equals("multAns")) {
            JsonArray jsonAnswers = jsonObj.getJsonArray("answers");
            int jsonAnswersSize = jsonAnswers.size();
            String[] convertedAnswers = new String[jsonAnswersSize];

            for(int i = 0; i < jsonAnswersSize; i++) {
                convertedAnswers[i] = jsonAnswers.getString(i);
            }

            return convertedAnswers;

        } else {
            return null;
        }

    }

}