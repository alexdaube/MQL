package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.Column;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;

import java.sql.Date;

public class SqlQueryBuilder implements QueryBuilder {
    private DbSchema schema;
    private SelectQuery selectQuery;
    private String table;
    private String attribute;
    private OperatorState operatorState;
    private Condition condition;
    private Condition prevCondition;
    private ComboCondition.Op junction;

    public SqlQueryBuilder(DbSchema schema) {
        this.schema = schema;
        this.selectQuery = new SelectQuery();
        this.operatorState = new EqualState(this);
    }

    public String build() {
        applyCondition(operatorState.apply());
        selectQuery.addCondition(condition.setDisableParens(false));
        selectQuery.validate();
        System.out.println(selectQuery.toString());
        return selectQuery.toString();
    }

    public SqlQueryBuilder withAllTablesColumns() {
        schema.getTables().forEach(selectQuery::addAllTableColumns);
        return this;
    }

    public SqlQueryBuilder withTableColumns(String table) {
        selectQuery.addAllTableColumns(schema.findTable(table));
        return this;
    }

    public SqlQueryBuilder withTableColumn(String table, String column) {
        selectQuery.addColumns(schema.findTable(table).findColumn(column));
        return this;
    }

    public SqlQueryBuilder withEntity(String entity) {
        table = entity;
        this.attribute = null;
        return this;
    }

    public SqlQueryBuilder withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public SqlQueryBuilder withEquals() {
        this.operatorState.withEquals();
        return this;
    }

    public SqlQueryBuilder withGreater() {
        this.operatorState.withGreater();
        return this;
    }

    public SqlQueryBuilder withLess() {
        this.operatorState.withLess();
        return this;
    }

    public SqlQueryBuilder withBetween() {
        this.operatorState.withBetween();
        return this;
    }

    public SqlQueryBuilder withVarchar(String value) {
        this.operatorState.withVarchar(value);
        return this;
    }

    public SqlQueryBuilder withInteger(int value) {
        this.operatorState.withInteger(value);
        return this;
    }

    public SqlQueryBuilder withDecimal(double value) {
        this.operatorState.withDecimal(value);
        return this;
    }

    public SqlQueryBuilder withDate(Date date) {
        this.operatorState.withDate(date);
        return this;
    }

    public SqlQueryBuilder and() {
        this.operatorState.and();
        return this;
    }

    public SqlQueryBuilder or() {
        this.operatorState.or();
        return this;
    }

    public void changeState(OperatorState operatorState) {
        this.operatorState = operatorState;
    }

    public void applyCondition(Condition condition) {
        this.condition = condition;
        if (junction != null) {
            this.condition = new ComboCondition(junction, prevCondition, condition).setDisableParens(true);
        }
        prevCondition = this.condition;
    }

    public void setJunction(ComboCondition.Op junction) {
        this.junction = junction;
    }

    public Column getAttribute() {
        return schema.findTable(table).findColumn(attribute);
    }
}
