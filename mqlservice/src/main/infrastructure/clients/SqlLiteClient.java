package infrastructure.clients;

import domain.DbClient;
import persistence.SQLHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SqlLiteClient implements DbClient {

    private final SQLHelper sqlHelper;

    public SqlLiteClient(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    @Override
    public List<Map<String, Object>> execute(String query) {
        ResultSet resultSet = sqlHelper.executeQuery(query);
        List<Map<String, Object>> result = new LinkedList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> columnMap = new HashMap<>();
                result.add(columnMap);

                for (int i = 1; i <= numberOfColumns; ++i) {
                    columnMap.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
