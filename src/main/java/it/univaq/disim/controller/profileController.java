package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.UserModel;
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
public class profileController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        try {
            UserModel userinfo = ContentLoaderController.loadProfile((Integer) session.getAttribute("userID"));
            System.out.println(1);
            request.setAttribute("userinfo",userinfo);
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
        } catch (SQLException e) {
            throw new ServletException("Retrieving products failed!", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();

        UserModel userup = new UserModel();
        userup.setId((Integer) session.getAttribute("userID"));
        userup.setName(request.getParameter("nome"));
        userup.setSurname(request.getParameter("cognome"));
        userup.setMail(request.getParameter("email"));
        userup.setPassword(request.getParameter("password"));
        userup.setBirthday(request.getParameter("datanascita"));

        UserInterface userdao = new UserDao();

        try {
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
        }

    }

}