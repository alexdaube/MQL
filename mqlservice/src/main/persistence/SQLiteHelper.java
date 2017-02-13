package persistence;

import java.sql.*;

public class SQLiteHelper implements SQLHelper {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void startConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            // test_data.db needs to be in projects' root for SQLite
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

                while (rs.next()) {
                    System.out.println("Name = " + rs.getString("Name"));
                    System.out.println("City = " + rs.getString("City"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
