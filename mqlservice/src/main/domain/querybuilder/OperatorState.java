package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.Condition;

import java.sql.Date;

public interface OperatorState {
    void withEquals();

    void withGreater();

    void withLess();

    void withVarchar(String value);

    void withInteger(int value);

    void withDecimal(double value);

    void withDate(Date date);

    void and();

    void or();

    Condition apply();

    void withBetween();
}
