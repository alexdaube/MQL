package persistence;

import java.sql.SQLException;

public interface SQLHelper {

    void startConnection() throws SQLException, ClassNotFoundException;

    void closeConnection();

    void readDataBase();

}