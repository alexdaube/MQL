package domain.query.builder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.JdbcEscape;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseState implements OperatorState {
    protected final SqlQueryBuilder queryBuilder;
    protected List<Object> values;

    public BaseState(SqlQueryBuilder queryBuilder) {
        this(queryBuilder, new LinkedList<>());
    }

    public BaseState(SqlQueryBuilder queryBuilder, List<Object> values) {
        this.queryBuilder = queryBuilder;
        this.values = values;
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
        values.add(JdbcEscape.date(date));
    }

    @Override
    public void and() {
        update();
        queryBuilder.setJunction(ComboCondition.Op.AND);
    }

    @Override
    public void or() {
        update();
        queryBuilder.setJunction(ComboCondition.Op.OR);
    }

    private void update() {
        queryBuilder.applyCondition(apply());
        values.clear();
    }
}
