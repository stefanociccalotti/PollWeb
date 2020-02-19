package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserInterface {

    Integer loginQuery(String u, String p);
    Integer loginClientQuery(String m,String p,String u);
    UserModel getUserInfo(Integer id);
    Integer updateUser(UserModel u) throws SQLException;
    ArrayList<UserModel> getCurators() throws SQLException;
    Integer deleteUser(Integer id)throws SQLException;
    ArrayList<UserModel> getSelParticipants(Integer questid) throws SQLException;
    Integer updateParticipants(UserModel participants,Integer questid) throws SQLException;
    Integer createParticipants(UserModel participants,Integer survey) throws SQLException;
    Integer newUser(UserModel newuser) throws SQLException;
    Integer deleteParticipant(String p)throws SQLException;
    String getTypeUser(String u)throws SQLException;
}
