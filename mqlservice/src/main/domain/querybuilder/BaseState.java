package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.ComboCondition;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseState implements OperatorState {
    protected final QueryBuilder queryBuilder;
    protected List<Object> values;

    public BaseState(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
        this.values = new LinkedList<>();
    }

    @Override
    public void withEquals() {
        queryBuilder.changeState(new EqualState(queryBuilder));
    }

    @Override
    public void withGreater() {
        queryBuilder.changeState(new GreaterState(queryBuilder));
    }

    @Override
    public void withLess() {
        queryBuilder.changeState(new LessState(queryBuilder));
    }

    @Override
    public void withBetween() {
        queryBuilder.changeState(new BetweenState(queryBuilder));
    }

    @Override
    public void withVarchar(String value) {
        values.add(value);
    }

    @Override
    public void withInteger(int value) {
        values.add(value);
    }

    @Override
    public void withDecimal(double value) {
        values.add(value);
    }

    @Override
    public void withDate(Date date) {
        values.add(date);
    }

    @Override
    public void and() {
        queryBuilder.updateQuery();
        queryBuilder.junction = ComboCondition.Op.AND;
        values = new LinkedList<>();
    }

    @Override
    public void or() {
        queryBuilder.updateQuery();
        queryBuilder.junction = ComboCondition.Op.OR;
        values = new LinkedList<>();
    }
}
