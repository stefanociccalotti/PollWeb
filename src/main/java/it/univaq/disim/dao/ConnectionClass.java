package it.univaq.disim.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public static Connection connection;
    public static Connection getConnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pollweb?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome","root","root");

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}