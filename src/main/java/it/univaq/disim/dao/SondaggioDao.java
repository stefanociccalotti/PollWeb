package it.univaq.disim.dao;

import it.univaq.disim.dao.Interface.SondaggioInterface;
import it.univaq.disim.model.Sondaggio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SondaggioDao implements SondaggioInterface {

    @Override
    public Integer createSondaggio(Sondaggio s) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();
        PreparedStatement ps;

        String sql = "INSERT INTO sondaggio (ID_Utente,Titolo,Apertura,Chiusura,Privato) VALUES (?,?,?,?,?)";
        ps=conn.prepareStatement(sql);

        //QUI INSERIAMO l'ID DELL'USER ps.setString(1,u.ID);
        ps.setString(2,s.getTitolo());
        ps.setString(3,s.getApertura());
        ps.setString(4,s.getChiusura());
        ps.setBoolean(5,s.isPrivato());

         return ps.executeUpdate();
    }

}
