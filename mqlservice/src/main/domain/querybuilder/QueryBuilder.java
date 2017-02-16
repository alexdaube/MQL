package domain.querybuilder;

import java.sql.Date;

public interface QueryBuilder {

    String build();

    QueryBuilder withEntity(String entity);

    QueryBuilder withAttribute(String attribute);

    QueryBuilder withEquals();

    QueryBuilder withGreater();

    QueryBuilder withLess();

    QueryBuilder withBetween();

    QueryBuilder withVarchar(String value);

    QueryBuilder withInteger(int value);

    QueryBuilder withDecimal(double value);

    QueryBuilder withDate(Date date);

    QueryBuilder and();

    QueryBuilder or();
}