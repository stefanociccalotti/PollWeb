package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.SurveyModel;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SurveyInterface {

    ArrayList<SurveyModel> getSurveyByUser(int id)throws SQLException;

}
