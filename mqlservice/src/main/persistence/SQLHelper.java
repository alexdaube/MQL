package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLHelper {

    void startConnection() throws SQLException, ClassNotFoundException;

    ResultSet executeQuery(String query);

    void closeConnection();

    void readDataBase();

}