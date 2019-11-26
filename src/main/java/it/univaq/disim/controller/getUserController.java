package it.univaq.disim.controller;

        import it.univaq.disim.dao.Interface.UserInterface;
        import it.univaq.disim.dao.UserDao;
        import it.univaq.disim.model.UserModel;

        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.sql.ResultSet;
        import java.sql.SQLException;

@WebServlet(name = "getUserController")
public class getUserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        System.out.println(user);

        UserInterface dao = new UserDao();
        /*try {
            ResultSet resultSet = dao.loginQuery(user,pass);

            if(resultSet.next()){
                request.setAttribute("user", user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
        rd.forward(request, response);
    }
}
