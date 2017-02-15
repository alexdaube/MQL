package domain;

import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class QueryBuilder {
    private DbSchema schema;
    private SelectQuery selectQuery;
    private List<Object> values;
    private String table;
    private String attribute;
    private String operator;
    private Condition condition;
    private Condition newCondition;
    private ComboCondition.Op junction;

    private QueryBuilder() {
        values = new LinkedList<>();
        schema = new DbSpec().addDefaultSchema();
        junction = null;
        DbTable employee = schema.addTable("Employee");
        employee.addColumn("name", "varchar", 255);
        employee.addColumn("age", "number", null);
        DbTable site = schema.addTable("Site");
        site.addColumn("name", "varchar", 255);
        selectQuery = new SelectQuery();
        schema.getTables().forEach(selectQuery::addAllTableColumns);
    }

    public static QueryBuilder create() {
        return new QueryBuilder();
    }

    public Query build() {
        updateQuery();
        selectQuery.addCondition(condition.setDisableParens(false));
        selectQuery.validate();
        System.out.println(selectQuery.toString());
        return null;
    }

    public QueryBuilder withEntity(String entity) {
        table = entity;
        return this;
    }

    public QueryBuilder withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public QueryBuilder withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public QueryBuilder withVarchar(String value) {
        this.values.add(value);
        return this;
    }

    public QueryBuilder withInteger(int value) {
        this.values.add(value);
        return this;
    }

    public QueryBuilder withDecimal(double value) {
        this.values.add(value);
        return this;
    }

    public QueryBuilder withDate(Date date) {
        this.values.add(date);
        return this;
    }

    public QueryBuilder and() {
        updateQuery();
        this.junction = ComboCondition.Op.AND;
        return this;
    }

    public QueryBuilder or() {
        updateQuery();
        this.junction = ComboCondition.Op.OR;
        return this;
    }

    // TODO: 14/02/17 this is temp
    private void mapOperator() {
        if (operator.equals("in")) {
            this.newCondition = new InCondition(schema.findTable(table).findColumn(attribute), values);
        } else if (operator.equals("is") || operator.equals("equal") || operator.equals("equals")) {
            this.newCondition = BinaryCondition.equalTo(schema.findTable(table).findColumn(attribute), values.get(0));
        }
    }

    private void updateQuery() {
        mapOperator();
        if (this.junction != null) {
            condition = new ComboCondition(this.junction).addConditions(condition, newCondition).setDisableParens(true);
        } else {
            condition = newCondition;
        }
        this.values = new LinkedList<>();
        this.operator = null;
        this.attribute = null;
    }
}
