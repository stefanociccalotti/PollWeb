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
                Integer userid = Integer.valueOf(request.getParameter("userid"));
                action_delete_gestore(userid,request,response);
                break;
            case "update":
                Integer userid2 = Integer.valueOf(request.getParameter("userid"));
                UserModel ug = new UserModel();
                ug.setId(userid2);
                ug.setName(request.getParameter("nomeg"));
                ug.setSurname(request.getParameter("cognomeg"));
                ug.setUsername(request.getParameter("userg"));
                ug.setMail(request.getParameter("mailg"));
                action_update_gestore(ug,request,response);
                break;
            case "new":
                UserModel newuser = new UserModel();
                newuser.setUsername(request.getParameter("usernamen"));
                newuser.setMail(request.getParameter("mailn"));
                newuser.setPassword(request.getParameter("passwordn"));
                action_create_gestore(newuser,request,response);
                break;
            default:
                action_getgestori(request, response);

        }
    }
    protected void action_create_gestore(UserModel newuser,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Integer res = userdao.newUser(newuser);
            if(res == 1 ){
                response.sendRedirect("curators");
            }else{
                //STAMPO ERRORE A SCHERMO!!
                System.out.println("errore!");
            }
        }catch(SQLException e){
            throw new ServletException("Retrieving products failed!", e);
        }

    }

    protected void action_delete_gestore(Integer userid,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Integer res = userdao.deleteUser(userid);
            if(res == 1 ){
                response.sendRedirect("curators");
            }else{
                //STAMPO ERRORE A SCHERMO!!
                System.out.println("errore!");
            }
        }catch(SQLException e){
            throw new ServletException("Retrieving products failed!", e);
        }

    }
    protected void action_update_gestore(UserModel gestoreup,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Integer res = userdao.updateUser(gestoreup);
            if(res == 1 ){
                response.sendRedirect("curators");
            }else{
                //STAMPO ERRORE A SCHERMO!!
                System.out.println("errore!");
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
            request.getRequestDispatcher("jsp/curators.jsp").forward(request, response); // Forward to JSP page to display them in a HTML table.
        } catch (SQLException e) {
            throw new ServletException("Retrieving products failed!", e);
        }

    }
}