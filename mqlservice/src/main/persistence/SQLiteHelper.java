package persistence;

import java.sql.*;

public class SQLiteHelper implements SQLHelper {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void startConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test_data.db");
            System.out.println("Connected to test database");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void readDataBase() {
        try {
            if (connection != null) {
                statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet rs = statement.executeQuery("select * from site");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        if (connection == null) {
            throw new RuntimeException("the connection has to be established to execute query...");
        }
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }

    public boolean isConnected() throws SQLException {
        return !this.connection.isClosed();
    }
}
