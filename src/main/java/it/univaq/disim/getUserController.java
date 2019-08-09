package it.univaq.disim;

import it.univaq.disim.dao.UserDao;
import it.univaq.disim.model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getUserController")
public class getUserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        UserDao dao = new UserDao();
        UserModel u = dao.getUserModel(id);

        request.setAttribute("userModel", u);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/showUserModel.jsp");
        rd.forward(request, response);
    }
}
