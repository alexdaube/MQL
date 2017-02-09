package domain;

import java.util.LinkedList;
import java.util.List;

public class QueryBuilder {
    private String entity;
    private String attribute;
    private List<String> operators;
    private List<String> values;

    public QueryBuilder() {
        operators = new LinkedList<>();
        values = new LinkedList<>();
    }

    public static QueryBuilder create() {
        return new QueryBuilder();
    }

    public Query build() {
        String stringValues = "(";
        stringValues = stringValues.concat(values.get(0));
        for (int i = 1; i < values.size(); i++) {
            stringValues = stringValues.concat(", " + values.get(i));
        }
        stringValues = stringValues.concat(")");

        System.out.println("SELECT(*) FROM " + entity + " WHERE " + attribute + " IN " + stringValues);
        return null;
    }

    public QueryBuilder withEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public QueryBuilder withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public QueryBuilder withOperator(String operator) {
        this.operators.add(operator);
        return this;
    }

    public QueryBuilder withValue(String value) {
        this.values.add(value);
        return this;
    }

    // TODO: 06/02/17 A revoir
    public QueryBuilder withJunction(String match) {
        return this;
    }
}
