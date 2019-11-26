package it.univaq.disim.dao;

import java.sql.*;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.model.UserModel;

public class UserDao implements UserInterface {

    @Override
    public Boolean loginQuery(String u, String p) {

        try
        {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.getConnection();

            String sql ="{CALL spUser_checkAuth(?,?)}";

            CallableStatement stmt = conn.prepareCall(sql);

            stmt.setString(1,u);
            stmt.setString(2,p);

            ResultSet rs = stmt.executeQuery();

           while(rs.next()){
               System.out.println(rs.getInt("code"));
               System.out.println(rs.getInt("aid"));
               return true;
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public UserModel getUserModel(int id) {

        UserModel u = new UserModel();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pollweb?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome","root","root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user where id = "+id);

            if(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setMail(rs.getString("mail"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        return u;
    }
}
