package infrastructure.clients;

import domain.DbClient;
import persistence.SQLHelper;
import services.query.AttributeDto;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SqlLiteClient implements DbClient {

    private final SQLHelper sqlHelper;

    public SqlLiteClient(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    @Override
    public List<List<AttributeDto>> execute(String query) {
        ResultSet resultSet = sqlHelper.executeQuery(query);
        List<List<AttributeDto>> result = new LinkedList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            while (resultSet.next()) {
                List<AttributeDto> columns = new LinkedList<>();
                result.add(columns);

                for (int i = 1; i <= numberOfColumns; ++i) {
                    AttributeDto attributeDto = new AttributeDto();
                    attributeDto.name = metaData.getColumnName(i);
                    attributeDto.value = resultSet.getString(i);
                    columns.add(attributeDto);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
