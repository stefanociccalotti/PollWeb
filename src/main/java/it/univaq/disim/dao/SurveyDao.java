package it.univaq.disim.dao;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.model.SurveyModel;
import java.sql.*;
import java.util.ArrayList;

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
}
