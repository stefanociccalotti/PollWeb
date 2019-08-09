package it.univaq.disim.dao;

import java.sql.*;
import it.univaq.disim.model.UserModel;

public class UserDao {

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
