package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.Column;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;

import java.sql.Date;

public class QueryBuilder {
    private DbSchema schema;
    private SelectQuery selectQuery;
    public String table;
    public String attribute;
    public Condition condition;
    public Condition newCondition;
    public ComboCondition.Op junction;
    private OperatorState operatorState;

    public QueryBuilder(DbSchema schema) {
        this.schema = schema;
        this.operatorState = new EqualState(this);
        junction = null;
        selectQuery = new SelectQuery();
    }

    public domain.Query build() {
        updateQuery();
        selectQuery.addCondition(condition.setDisableParens(false));
        selectQuery.validate();
        System.out.println(selectQuery.toString());
        return null;
    }

    public QueryBuilder withAllTableColumns() {
        schema.getTables().forEach(selectQuery::addAllTableColumns);
        return this;
    }

    public QueryBuilder withTableColumns(String table) {
        selectQuery.addAllTableColumns(schema.findTable(table));
        return this;
    }

    public QueryBuilder withTableColumn(String table, String column) {
        selectQuery.addColumns(schema.findTable(table).findColumn(column));
        return this;
    }

    public QueryBuilder withEntity(String entity) {
        table = entity;
        this.attribute = null;
        return this;
    }

    public QueryBuilder withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public QueryBuilder withEquals() {
        this.operatorState.withEquals();
        return this;
    }

    public QueryBuilder withGreater() {
        this.operatorState.withGreater();
        return this;
    }

    public QueryBuilder withLess() {
        this.operatorState.withLess();
        return this;
    }

    public QueryBuilder withVarchar(String value) {
        this.operatorState.withVarchar(value);
        return this;
    }

    public QueryBuilder withInteger(int value) {
        this.operatorState.withInteger(value);
        return this;
    }

    public QueryBuilder withDecimal(double value) {
        this.operatorState.withDecimal(value);
        return this;
    }

    public QueryBuilder withDate(Date date) {
        this.operatorState.withDate(date);
        return this;
    }

    public QueryBuilder and() {
        this.operatorState.and();
        return this;
    }

    public QueryBuilder or() {
        this.operatorState.or();
        return this;
    }

    public void updateQuery() {
        this.operatorState.apply();
        if (this.junction != null) {
            condition = new ComboCondition(this.junction).addConditions(condition, newCondition).setDisableParens(true);
        } else {
            condition = newCondition;
        }
    }

    public void changeState(OperatorState operatorState) {
        this.operatorState = operatorState;
    }

    public Column getAttribute() {
        return schema.findTable(table).findColumn(attribute);
    }

    public void withBetween() {
        this.operatorState.withBetween();
    }
}
