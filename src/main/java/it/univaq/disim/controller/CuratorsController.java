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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CuratorsController")
public class CuratorsController extends HttpServlet {
    UserInterface userdao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String action = "getgestori";
        try {
            processRequest(request,response,action);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        try {
            processRequest(request,response,action);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String action) throws IOException, ServletException {

        switch(action){
            case "delete":
                action_delete_gestore(request,response);
                break;
            case "update":
                action_update_gestore(request,response);
                break;
            case "new":
                action_create_gestore(request,response);
                break;
            default:
                action_getgestori(request, response);

        }
    }
    protected void action_create_gestore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserModel newuser = new UserModel();
        newuser.setUsername(request.getParameter("usernamen"));
        newuser.setMail(request.getParameter("mailn"));
        newuser.setPassword(request.getParameter("passwordn"));
        try{
            PrintWriter out;
            out = response.getWriter();
            Integer res = userdao.newUser(newuser);
            if(res == 1 ){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Gestore Creato con successo!!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
                //response.sendRedirect("curators");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ERRORE! Username/e-mail duplicata!!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
            }
        }catch(SQLException e){
            throw new ServletException("Retrieving products failed!", e);
        }

    }

    protected void action_delete_gestore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer userid = Integer.valueOf(request.getParameter("userid"));
        try{
            PrintWriter out;
            out = response.getWriter();
            Integer res = userdao.deleteUser(userid);
            if(res == 1 ){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Gestore Eliminato con successo!!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
                //response.sendRedirect("curators");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ERRORE INTERNO AL SERVER!!!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
            }
        }catch(SQLException e){
            throw new ServletException("Retrieving products failed!", e);
        }

    }
    protected void action_update_gestore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer userid2 = Integer.valueOf(request.getParameter("userid"));
        UserModel ug = new UserModel();
        ug.setId(userid2);
        ug.setName(request.getParameter("nomeg"));
        ug.setSurname(request.getParameter("cognomeg"));
        ug.setUsername(request.getParameter("userg"));
        ug.setMail(request.getParameter("mailg"));
        try{
            PrintWriter out;
            out = response.getWriter();
            Integer res = userdao.updateUser(ug);
            if(res == 1 ){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update Avvenuto con successo!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
                //response.sendRedirect("curators");
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Errore durante l'update dei dati!!');");
                out.println("location='/web-engineering-pollweb/curators';");
                out.println("</script>");
            }
        }catch(SQLException e){
            throw new ServletException("Retrieving products failed!", e);
        }

    }

    protected void action_getgestori(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<UserModel> listcurators;
        try {
            listcurators = userdao.getCurators();
            request.setAttribute("curatorslist",listcurators);
            request.setAttribute("pageCss","./resources/dist/css/curators.css");
            request.getRequestDispatcher("jsp/curators.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
        } catch (SQLException e) {
            throw new ServletException("Retrieving products failed!", e);
        }

    }
}