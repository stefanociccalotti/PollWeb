package it.univaq.disim.controller;


import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.UserModel;
import it.univaq.disim.security.SecurityLayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ParticipantsController")
public class ParticipantsController extends HttpServlet {
    UserInterface userdao = new UserDao();
    ArrayList<UserModel> lisparticipants;
    public static Integer questid = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action="";
        processRequest(action,request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        processRequest(action,request,response);

    }

    protected void processRequest(String action,HttpServletRequest request, HttpServletResponse response){
        switch(action){
            case "updateparticipants":
                action_updtaeParticipants(request,response);
                break;
            case "createparticipants":
                action_createParticipants(request,response);
                break;
            default:
                action_getParticipantsController(request,response);
        }
    }

    private void action_createParticipants(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UserModel upparticipants = new UserModel();
        upparticipants.setMail(mail);
        upparticipants.setPassword(password);
        try {
            PrintWriter out;
            out = response.getWriter();
            Integer res = userdao.createParticipants(upparticipants,questid);
            System.out.println(res);
            if(res == 200){
                request.setAttribute("lisparticipants",lisparticipants);
                request.setAttribute("pageCss","./resources/dist/css/participants.css");
                response.sendRedirect("participantsEditor?surveyid=" + questid+"&a=add"); // Forward to JSP page to display them in a HTML table.
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Errore durante l'aggiunta del partecipante'!!');");
                out.println("location='/web-engineering-pollweb/profile';");
                out.println("</script>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void action_updtaeParticipants(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Integer idpart = Integer.valueOf(request.getParameter("idparticipants"));

        UserModel upparticipants = new UserModel();
        upparticipants.setMail(mail);
        upparticipants.setPassword(password);
        upparticipants.setId(idpart);

        try {
            PrintWriter out;
            out = response.getWriter();
            Integer res = userdao.updateParticipants(upparticipants,questid);
            System.out.println(res);
            if(res == 1){
                request.setAttribute("lisparticipants",lisparticipants);
                request.setAttribute("pageCss","./resources/dist/css/participants.css");
                response.sendRedirect("participantsEditor?surveyid=" + questid); // Forward to JSP page to display them in a HTML table.
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Errore durante l'update dei dati del partecipante!!');");
                out.println("location='/web-engineering-pollweb/profile';");
                out.println("</script>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_getParticipantsController(HttpServletRequest request, HttpServletResponse response) {
        Integer questid = Integer.valueOf(request.getParameter("surveyid"));
        String a = request.getParameter("a");
        //aggiorno l'id del survey per modificare i partecipanti
        this.questid = questid;

        System.out.println("AA:"+ a);

        if(a != null){
            try {
                ArrayList<UserModel> a1 = new ArrayList<>();
                a1.add(new UserModel(1,"","","","","","",""));
                request.setAttribute("pageCss","./resources/dist/css/participants.css");
                request.setAttribute("lisparticipants",a1);
                request.setAttribute("btnname","Aggiungi");
                request.setAttribute("action","createparticipants");
                request.getRequestDispatcher("jsp/participants.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                lisparticipants = userdao.getSelParticipants(questid);
                request.setAttribute("pageCss","./resources/dist/css/participants.css");
                request.setAttribute("lisparticipants", lisparticipants);
                request.setAttribute("btnname", "Modifica");
                request.setAttribute("action", "updateparticipants");
                request.getRequestDispatcher("jsp/participants.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
