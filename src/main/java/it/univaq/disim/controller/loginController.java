package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.security.SecurityLayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginController")
public class loginController extends HttpServlet {

    private UserInterface dao = new UserDao();
    String uri ="";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String surveyURL = request.getParameter("survey");


        if(surveyURL == null){
            try {
                response.sendRedirect("/web-engineering-pollweb/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("survey",surveyURL);
            try {
                request.getRequestDispatcher("jsp/loginClient.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        String action = request.getParameter("action");
        try {
            processRequest(request,response,action);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String action) throws IOException, ServletException, SQLException {

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
        String url = request.getParameter("survey");

        //mi segno la uri della richiesta per poi riutilizzarla in caso di errore.
        uri = "http://localhost:8080/web-engineering-pollweb/surveyClient?survey="+url;

        Integer iduser = dao.loginClientQuery(mail,pass,url);
        System.out.println(mail + " " +pass + " " + url);

        if(iduser != null){
            //vai alla compilazione del sondaggio
            HttpSession session=request.getSession();
            session.setAttribute("client",mail);
            response.sendRedirect("surveyClient?survey=" + url);

        }else{
            //errore nella login con messaggio
            //request.getRequestDispatcher("errore").forward(request, response);

            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.println("<html>\n" +
                    "<body onload=\"myFunction()\">\n" +
                    "\n" +
                    "<script>\n" +
                    "function myFunction() {\n" +
                    "  alert(\"ERRORE USERNAME/PASSWORD ERRATI!\");\n" +
                    "  window.location.href =\""+uri+"\";\n" +
                    "}\n" +
                    "</script>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        }

    }
    protected void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        Integer iduser = dao.loginQuery(user,pass);
        String type = dao.getTypeUser(user);

        if(iduser != null){
            SecurityLayer.createSession(request,user,type,iduser);
        }

        //RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
        //rd.forward(request,response);

        response.sendRedirect("/web-engineering-pollweb/home");
    }
}
