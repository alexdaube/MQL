package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.dbspec.Column;

import java.sql.Date;

public interface QueryBuilder {

    domain.Query build();

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