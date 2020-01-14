package it.univaq.disim.controller;


import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ParticipantsController")
public class ParticipantsController extends HttpServlet {
    UserInterface userdao = new UserDao();
    ArrayList<UserModel> lisparticipants;
    public static Integer questid = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer questid = Integer.valueOf(request.getParameter("surveyid"));

        //aggiorno l'id del survey per modificare i partecipanti
        this.questid =questid;

        try {
            lisparticipants = userdao.getSelParticipants(questid);
            request.setAttribute("lisparticipants",lisparticipants);
            request.getRequestDispatcher("jsp/participants.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
        } catch (SQLException e) {
            throw new ServletException("Retrieving products failed!", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Integer idpart = Integer.valueOf(request.getParameter("idparticipants"));

        UserModel upparticipants = new UserModel();
        upparticipants.setMail(mail);
        upparticipants.setPassword(password);
        upparticipants.setId(idpart);


        try {
            Integer res = userdao.updateParticipants(upparticipants,questid);
            System.out.println(res);
            if(res == 1){
                request.setAttribute("lisparticipants",lisparticipants);
                response.sendRedirect("participantsEditor?surveyid=" + questid); // Forward to JSP page to display them in a HTML table.
            }else{
                //STAMPO UN ERRORE
            }

        } catch (SQLException e) {
            throw new ServletException("Retrieving products failed!", e);
        }
    }
}
