package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserInterface {

    Integer loginQuery(String u, String p);
    UserModel getUserModel(int id);
}
