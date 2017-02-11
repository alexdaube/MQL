import persistence.SQLHelper;
import persistence.SQLiteHelper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        SQLHelper sqlHelper = new SQLiteHelper();
        try {
            sqlHelper.startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        QueryController queryController = new QueryController();
        queryController.initializeEndPoints();
    }
}
