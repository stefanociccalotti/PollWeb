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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "profileController")
public class ProfileController extends HttpServlet {
   private UserInterface userdao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest("",request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        processRequest(action,request,response);
    }

    protected void processRequest(String action,HttpServletRequest request, HttpServletResponse response){
        switch(action){
            case "updateprofile":
                action_updateprofile(request,response);
                break;
            default:
                action_getProfile(request,response);
        }
    }

    private void action_getProfile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();

        UserModel userinfo = userdao.getUserInfo((Integer) session.getAttribute("userID"));
        request.setAttribute("userinfo",userinfo);
        try {
            request.setAttribute("pageCss","./resources/dist/css/profile.css");
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_updateprofile(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter out;
            out = response.getWriter();

            HttpSession session=request.getSession();

            UserModel userup = new UserModel();
            userup.setId((Integer) session.getAttribute("userID"));
            userup.setName(request.getParameter("nome"));
            userup.setSurname(request.getParameter("cognome"));
            userup.setMail(request.getParameter("email"));
            userup.setPassword(request.getParameter("password"));
            userup.setBirthday(request.getParameter("datanascita"));

            UserInterface userdao = new UserDao();


            Integer result = userdao.updateUser(userup);

            if(result == 1){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Avvenuto con successo!');");
                out.println("location='/web-engineering-pollweb/profile';");
                out.println("</script>");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Errore durante l'update dei dati!!');");
                out.println("location='/web-engineering-pollweb/profile';");
                out.println("</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
