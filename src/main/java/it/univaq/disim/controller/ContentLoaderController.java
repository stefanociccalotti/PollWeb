package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.SurveyModel;
import it.univaq.disim.model.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContentLoaderController {

    public ContentLoaderController() {}

    public static ArrayList<SurveyModel> loadHome(Integer id) throws SQLException {

        ArrayList<SurveyModel> listsurvey= new ArrayList<>();

        SurveyInterface surveydao = new SurveyDao();

        return listsurvey = surveydao.getSurveyByUser(id);

    }

    public static UserModel loadProfile(Integer id) throws SQLException {

        UserModel u = new UserModel();

        UserInterface userdao = new UserDao();

        return u = userdao.getUserInfo(id);

    }

}