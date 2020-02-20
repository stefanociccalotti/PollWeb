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
import java.util.Map;
import java.util.TreeMap;

public class SurveyDao implements SurveyInterface {

    private ArrayList<SurveyModel> listsurvey = new ArrayList<>();
    ConnectionPool connectionPool = new ConnectionPool();
    Connection conn = null;
    PreparedStatement st;

    @Override
    public Integer submitSurveyAndQuestions(String data, Integer userID) throws SQLException {

        JsonObject jsonData = jsonFromString(data);
        Integer code = 500;

        try {//TODO: aggiungere controllo sull'utente (deve combaciare chi ha creato il sondaggio con l'id che si vuole modificare)

            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

            if(!jsonData.isNull("id")) {
                code = updateSurvey(jsonData);
                if(code == 200) updateQuestions(jsonData.getJsonArray("questions"),jsonData.getInt("id"));
            } else {
                ResultSet rs = insertSurvey(jsonData, userID);
                rs.next();
                code = rs.getInt("code");
                if(code == 200) insertQuestions(jsonData.getJsonArray("questions"),rs.getInt("survey"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;

    }

    private void insertQuestions(JsonArray jsonQuestions, Integer survey) {
        try {

            for(int i = 0; i < jsonQuestions.size(); i++) {
                JsonObject question = (JsonObject) jsonQuestions.get(i);

                String sql = "{CALL spQuestion_insert(?,?,?,?,?)}";
                CallableStatement stmt = conn.prepareCall(sql);

                stmt.setInt(1,question.getInt("mandatory"));
                stmt.setInt(2,question.getInt("number"));
                stmt.setString(3,question.getJsonObject("text").toString());
                stmt.setString(4,question.getString("note"));
                stmt.setInt(5,survey);

                stmt.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet insertSurvey(JsonObject jsonData, Integer userID) {

        String sql = "{CALL spSurvey_insert(?,?,?,?,?,?,?)}";//url,privacy,status,title,opening,closing,user
        ResultSet rs = null;

        try {
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1,"");
            stmt.setString(2,jsonData.getString("privacy"));
            stmt.setString(3,jsonData.getString("status"));
            stmt.setString(4,jsonData.getString("title"));
            stmt.setString(5,jsonData.getString("opening"));
            stmt.setString(6,jsonData.getString("closing"));
            stmt.setInt(7,userID);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    private Integer updateSurvey(JsonObject jsonData) {

        String sql = "{CALL spSurvey_update(?,?,?,?,?,?)}";
        Integer code = 500;

        try {
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1,jsonData.getInt("id"));
            stmt.setString(2,jsonData.getString("privacy"));
            stmt.setString(3,jsonData.getString("status"));
            stmt.setString(4,jsonData.getString("title"));
            stmt.setString(5,jsonData.getString("opening"));
            stmt.setString(6,jsonData.getString("closing"));
            ResultSet rs = stmt.executeQuery();

            rs.next();

            code = rs.getInt("code");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return code;
    }

    private void updateQuestions(JsonArray jsonQuestions, Integer survey) {

        try {
            String sql = "{CALL spQuestion_deleteBySurvey(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1,survey);
            stmt.executeQuery();

            insertQuestions(jsonQuestions, survey);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            st.setString(1,"http://localhost:8080/web-engineering-pollweb/surveyClient?survey="+url);

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
    public Integer setAnswerUser(TreeMap<String,String> answer, Integer ids) {
        Integer rs =0;
        String idcompiler ="";

        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql = "{CALL pollweb.spAnswer_insertFirst(?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, String.valueOf(answer.firstEntry().getValue()));
            stmt.setInt(2, Integer.valueOf(answer.firstEntry().getKey()));
            stmt.setInt(3, ids);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                idcompiler = resultSet.getString("new_id_compiler");
            }
            if(idcompiler.equals("")){
                return rs;
            }else{
                answer.pollFirstEntry();
                for(Map.Entry<String,String> entry : answer.entrySet() ) {
                    String key = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    System.out.println(value);
                    String sql2 = "{CALL pollweb.spAnswer_insert(?,?,?,?)}";
                    CallableStatement stmt2 = conn.prepareCall(sql2);
                    stmt2.setInt(1, Integer.valueOf(idcompiler));
                    stmt2.setString(2, value);
                    stmt2.setInt(3, Integer.valueOf(key));
                    stmt2.setInt(4, ids);
                    stmt2.executeQuery();
                     }
                rs=1;
            }
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

            //ritorno il risultato della query
            rs = st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Integer publishSurvey(Integer idsurvey) throws SQLException {
        Integer rs =0;
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql="UPDATE survey SET status='pubblicato', url=? where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1,"http://localhost:8080/web-engineering-pollweb/surveyClient?survey=sondaggio"+idsurvey);
            st.setInt(2,idsurvey);

            //ritorno il risultato della query
            rs = st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;

    }

    @Override
    public ArrayList<QuestionModel> getSurveyResult(Integer idsurvey){
        ArrayList<QuestionModel> listanswer = new ArrayList<>();
        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
            String sql = "{CALL pollweb.spAnswer_getBySurvey(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, idsurvey);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject jsn = new JSONObject(rs.getString("qtext"));
                QuestionModel tmp = new QuestionModel(rs.getInt("id_compiler"),0,0,"",null,rs.getString("text"),String.valueOf(jsn.get("question")),rs.getInt("number"));
                listanswer.add(tmp);
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