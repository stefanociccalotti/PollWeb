package it.univaq.disim.dao.Interface;

import it.univaq.disim.model.Sondaggio;
import java.sql.SQLException;

public interface SondaggioInterface {

    Integer createSondaggio(Sondaggio s)throws SQLException;
}
