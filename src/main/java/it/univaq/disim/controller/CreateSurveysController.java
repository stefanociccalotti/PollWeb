package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.SurveyModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CreateSurveyC")
public class CreateSurveysController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        SurveyInterface surveyDao = new SurveyDao();

        try {

            ArrayList<SurveyModel> list = surveyDao.getSurveyByUser((Integer) session.getAttribute("userID"), "viewSurveys");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
