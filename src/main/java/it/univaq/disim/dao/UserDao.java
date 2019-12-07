package it.univaq.disim.dao;

import java.sql.*;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.model.UserModel;

public class UserDao implements UserInterface {

    ConnectionClass connectionClass = new ConnectionClass();
    Connection conn = connectionClass.getConnection();
    PreparedStatement st;

    @Override
    public Integer loginQuery(String u, String p) {

        try
        {

            String sql ="{CALL spUser_checkAuth(?,?)}";

            CallableStatement stmt = conn.prepareCall(sql);

            stmt.setString(1,u);
            stmt.setString(2,p);

            ResultSet rs = stmt.executeQuery();

           while(rs.next()){
               if(rs.getInt("code") == 200){
                   return rs.getInt("aid");
               }
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserModel getUserInfo(Integer id) {

        UserModel u = new UserModel();

        try{
            String sql="select * from user where id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,id);

            //ritorno il sisultato della query
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setType(rs.getString("type"));
                u.setMail(rs.getString("mail"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setBirthday(rs.getString("birthdate"));
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        return u;
    }

    @Override
    public Integer updateUser(UserModel u) throws SQLException {

            String sql="UPDATE user SET password=?, mail=?, name=?, surname=?, birthdate=? WHERE id=? ";
            st = conn.prepareStatement(sql);
            st.setString(1,u.getPassword());
            st.setString(2,u.getMail());
            st.setString(3,u.getName());
            st.setString(4,u.getSurname());
            st.setString(5,u.getBirthday());
            st.setInt(6,u.getId());

            int resultSet = st.executeUpdate();

            return resultSet;

    }
}
