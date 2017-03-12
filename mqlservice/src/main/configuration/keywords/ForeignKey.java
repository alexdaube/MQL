package configuration.keywords;

public class ForeignKey {
    private final String TableName;
    private final String fromColumn;
    private final String toColumn;

    public ForeignKey(String tableName, String fromColumn, String toColumn) {
        TableName = tableName;
        this.fromColumn = fromColumn;
        this.toColumn = toColumn;
    }

    public String getTableName() {
        return TableName;
    }

    public String getFromColumn() {
        return fromColumn;
    }

    public String getToColumn() {
        return toColumn;
    }
}
