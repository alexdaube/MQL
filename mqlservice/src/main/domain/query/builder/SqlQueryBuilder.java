package domain.query.builder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.NotCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.Column;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

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
    private boolean not;

    public SqlQueryBuilder(DbSchema schema) {
        this.schema = schema;
        this.selectQuery = new SelectQuery();
        this.operatorState = new EqualState(this);
        this.not = false;
    }

    public String build() {
        operatorState.update();
        selectQuery.addCondition(condition.setDisableParens(false));
        selectQuery.validate();
        return selectQuery.toString();
    }

    @Override
    public String currentTable() {
        return table;
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

    public QueryBuilder withJoin(String fromTable, String toTable, String fromAttribute, String toAttribute) {
        DbTable fTable = schema.findTable(fromTable);
        DbTable tTable = schema.findTable(toTable);
        selectQuery.addJoin(SelectQuery.JoinType.INNER, fTable, tTable, fTable.findColumn(fromAttribute),
                tTable.findColumn(toAttribute));
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

    public SqlQueryBuilder withOperator(OperatorType operator) {
        this.operatorState.withOperator(operator);
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
        if (this.not) {
            this.condition = new NotCondition(this.condition);
        }
        if (junction != null) {
            this.not = false;
            this.condition = new ComboCondition(junction, prevCondition, this.condition).setDisableParens(true);
        }
        prevCondition = this.condition;
    }

    public void toggleNot() {
        this.not = !this.not;
    }

    public void setJunction(ComboCondition.Op junction) {
        this.junction = junction;
    }

    public Column getAttribute() {
        return schema.findTable(table).findColumn(attribute);
    }

    public Condition getCondition() {
        return condition;
    }
}
