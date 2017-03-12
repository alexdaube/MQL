package domain.query.builder;

import java.sql.Date;

public interface QueryBuilder {

    String build();

    QueryBuilder withEntity(String entity);

    QueryBuilder withJoin(String fromTable, String toTable, String fromAttribute, String toAttribute);

    QueryBuilder withAttribute(String attribute);

    QueryBuilder withOperator(OperatorType operator);

    QueryBuilder withVarchar(String value);

    QueryBuilder withInteger(int value);

    QueryBuilder withDecimal(double value);

    QueryBuilder withDate(Date date);

    QueryBuilder and();

    QueryBuilder or();
}