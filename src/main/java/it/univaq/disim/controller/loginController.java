package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginController")
public class loginController extends HttpServlet {

    private UserInterface dao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surveyURL = request.getParameter("url");
        System.out.println(surveyURL);
        if(surveyURL == null){
            response.sendRedirect("/web-engineering-pollweb/");
        }else{
            request.setAttribute("url",surveyURL);
            request.getRequestDispatcher("jsp/loginClient.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        processRequest(request,response,action);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String action) throws IOException, ServletException {

        switch(action){
            case "loginclient":
                action_loginclient(request,response);
                break;
            default:
                action_login(request,response);

        }
    }

    protected void action_loginclient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        String url = request.getParameter("url");

        Integer iduser = dao.loginClientQuery(mail,pass);
        System.out.println(mail + " " +pass + " " + url);

        if(iduser != null){
            //vai alla compilazione del sondaggio
            HttpSession session=request.getSession();
            session.setAttribute("client",mail);
            response.sendRedirect("surveyClient?url=" + url);

        }else{
            //errore nella login con messaggio
            //request.getRequestDispatcher("errore").forward(request, response);
            System.out.println("ERRORE USERNAME E PASSWORD DB");
        }

    }
    protected void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        Integer iduser = dao.loginQuery(user,pass);

        if(iduser != null){
            HttpSession session=request.getSession();
            session.setAttribute("userID",iduser);
            session.setAttribute("user",user);
        }

        //RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
        //rd.forward(request,response);

        response.sendRedirect("/web-engineering-pollweb/home");
    }
}
