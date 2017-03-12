package domain.query.builder;

import com.healthmarketscience.sqlbuilder.Condition;

import java.sql.Date;

public interface OperatorState {
    void withOperator(OperatorType operator);

    void withVarchar(String value);

    void withInteger(int value);

    void withDecimal(double value);

    void withDate(Date date);

    void and();

    void or();

    Condition apply();
}
