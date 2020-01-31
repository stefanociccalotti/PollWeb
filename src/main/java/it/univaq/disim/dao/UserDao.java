package it.univaq.disim.dao;

import java.sql.*;
import java.util.ArrayList;

import it.univaq.disim.dao.Interface.UserInterface;
import it.univaq.disim.model.UserModel;

import javax.sql.DataSource;

public class UserDao implements UserInterface {

    //ConnectionClass connectionClass = new ConnectionClass();
    //Connection conn = connectionClass.getConnection();
    //PreparedStatement st;
    ConnectionPool connectionPool = new ConnectionPool();
    Connection conn = null;
    PreparedStatement st;

    @Override
    public Integer loginQuery(String u, String p) {

        try
        {

            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer loginClientQuery(String m, String p) {
        try
        {

            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();

            String sql ="{CALL spParticipant_checkAuth(?,?)}";

            CallableStatement stmt = conn.prepareCall(sql);

            stmt.setString(1,m);
            stmt.setString(2,p);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                if(rs.getInt("code") == 200){
                    return rs.getInt("asurvey");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserModel getUserInfo(Integer id) {

        UserModel u = new UserModel();

        try{
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
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

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

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
    @Override
    public ArrayList<UserModel> getCurators() throws SQLException {

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

        String sql="SELECT * from user WHERE type=?";
        st = conn.prepareStatement(sql);
        st.setString(1,"curator");
        ArrayList<UserModel> lc = new ArrayList<>();

        ResultSet rs = st.executeQuery();

        while (rs.next()){
            lc.add(new UserModel(rs.getInt("id"),rs.getString("username"),"","curator",rs.getString("mail"),rs.getString("name"),rs.getString("surname"),""));
        }
        return lc;
    }

    @Override
    public Integer deleteUser(Integer id) throws SQLException {

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

        String sql="DELETE from user WHERE id =?";
        st = conn.prepareStatement(sql);
        st.setInt(1,id);

        int result = st.executeUpdate();

        return result;
    }

    @Override
    public ArrayList<UserModel> getSelParticipants(Integer questid) throws SQLException {

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

        ArrayList<UserModel> lparticipants = new ArrayList<>();
        String sql = "SELECT id,mail,password from participant WHERE survey=?";
        st = conn.prepareStatement(sql);
        st.setInt(1,questid);

        ResultSet rs = st.executeQuery();

        while (rs.next()){
            lparticipants.add(new UserModel(rs.getInt("id"),"",rs.getString("password"),"",rs.getString("mail"),"","",""));
        }
        return lparticipants;
    }

    @Override
    public Integer updateParticipants(UserModel participants, Integer questid) throws SQLException {

        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

        String sql = "UPDATE participant set mail=? ,password=? WHERE survey=? AND id=?";
        st = conn.prepareStatement(sql);
        st.setString(1,participants.getMail());
        st.setString(2,participants.getPassword());
        st.setInt(3,questid);
        st.setInt(4,participants.getId());

        int resultSet = st.executeUpdate();

        return resultSet;
    }

    @Override
    public  Integer newUser(UserModel newuser) throws SQLException{
        try {
            DataSource dataSource = connectionPool.setUpPool();
            conn = dataSource.getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

        String sql = "INSERT INTO user(username,password,type,mail) VALUES (?,?,?,?)";
        st = conn.prepareStatement(sql);
        st.setString(1,newuser.getUsername());
        st.setString(2,newuser.getPassword());
        st.setString(3,"curator");              //MOMENTANEO DA VEDERE!!
        st.setString(4,newuser.getMail());

        int result = st.executeUpdate();

        return result;

    }
}
