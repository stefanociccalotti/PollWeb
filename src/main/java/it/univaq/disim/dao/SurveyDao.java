package it.univaq.disim.dao;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.model.QuestionModel;
import it.univaq.disim.model.SurveyModel;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import javax.json.*;
import javax.sql.DataSource;
import java.io.StringReader;

public class SurveyDao implements SurveyInterface {

    private ArrayList<SurveyModel> listsurvey = new ArrayList<>();
    ConnectionPool connectionPool = new ConnectionPool();
    Connection conn = null;
    PreparedStatement st;

    @Override
    public Integer insertSurveyAndQuestions(String data) throws SQLException {

        JsonObject jsonData = jsonFromString(data);

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

            String sql = "{CALL spSurvey_update(?,?,?,?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1,jsonData.getInt("id"));
            stmt.setInt(2,jsonData.getInt("privacy"));
            stmt.setString(3,jsonData.getString("status"));
            stmt.setString(4,jsonData.getString("title"));
            stmt.setString(5,jsonData.getString("opening"));
            stmt.setString(6,jsonData.getString("closing"));
            ResultSet rs = stmt.executeQuery();

            rs.next();

            Integer code = rs.getInt("code");

                if(code == 200) {

                    String dsql = "{CALL spQuestion_deleteBySurvey(?)}";
                    CallableStatement dstmt = conn.prepareCall(dsql);

                    dstmt.setInt(1,jsonData.getInt("id"));

                    dstmt.executeQuery();

                    JsonArray jsonQuestions = jsonData.getJsonArray("questions");

                    for(int i = 0; i < jsonQuestions.size(); i++) {
                        JsonObject question = (JsonObject) jsonQuestions.get(i);

                        String qsql = "{CALL spQuestion_insert(?,?,?,?,?)}";
                        CallableStatement qstmt = conn.prepareCall(qsql);

                        qstmt.setInt(1,question.getInt("mandatory"));
                        qstmt.setInt(2,question.getInt("number"));
                        qstmt.setString(3,question.getJsonObject("text").toString());
                        qstmt.setString(4,question.getString("note"));
                        qstmt.setInt(5,jsonData.getInt("id"));

                        qstmt.executeQuery();

                    }

                }

            return code;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 200;

    }

    @Override
    public ArrayList<SurveyModel> getSurveyByUser(int id, String page) throws SQLException {
        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

            String sql = "{CALL spSurvey_getByUser(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listsurvey.add(new SurveyModel(rs.getInt("id"), rs.getString("url"), rs.getString("privacy"), rs.getString("status"),
                        rs.getString("title"), rs.getString("opening"), rs.getString("closing"), rs.getInt("user"), page));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listsurvey;
    }

    @Override
    public ArrayList<Object> getSurveyAndQuestionsById(Integer surveyId) throws SQLException {

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

            String sql = "{CALL pollweb.spQuestion_getBySurvey(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, surveyId);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Object> fullSurvey = new ArrayList<>();
            ArrayList<QuestionModel> questionsList = new ArrayList<>();
            Integer numberOfQuestions = 0;

            rs.next();

            Integer code = rs.getInt("code");
            System.out.println(code);
            switch (code) {
                case 2:
                    //CODICE 2: SONDAGGIO ESISTE E CI SONO DOMANDE. INOLTRE SALVO IL SONDAGGIO SOLO QUANDO CICLO SULLA PRIMA ROW.
                    fullSurvey.add(getSurvey(rs));
                    questionsList.add(getQuestion(rs));
                    numberOfQuestions++;
                    //NELLE ALTRE ROWS SI DEVONO SALVARE SOLO LE INFORMAZIONI DELLE DOMANDE (SE CI SONO)
                    while (rs.next()) {
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getSurveyId(String url){
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql="SELECT * from survey s join question q on(s.id=q.survey) WHERE s.url=?";
            st = conn.prepareStatement(sql);
            st.setString(1,url);

            //ritorno il sisultato della query
            ResultSet rs = st.executeQuery();
            while (rs.next()){
              return rs.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer setAnswerUser(JSONObject json,Integer ids) throws SQLException {
        Integer rs =0;
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql="INSERT INTO answer (id_compiler,text,question,survey) values(1,?,1,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, String.valueOf(json));
            st.setInt(2, ids);

            //ritorno il sisultato della query
            rs = st.executeUpdate();
            //return rs.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Integer surveyClose(Integer idsurvey) throws SQLException {
        Integer rs =0;
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql="UPDATE survey SET status='chiuso', url='null' where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,idsurvey);

            //ritorno il sisultato della query
            rs = st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ArrayList<String> getSurveyResult(Integer idsurvey) throws SQLException {
        ArrayList<String> listanswer = new ArrayList<>();
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql = "{CALL pollweb.spAnswer_getBySurvey(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, idsurvey);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listanswer.add(rs.getString("text"));
                listanswer.add("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listanswer;
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