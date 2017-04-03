package domain.query.builder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.JdbcEscape;
import com.healthmarketscience.sqlbuilder.NotCondition;
import domain.InvalidQueryException;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseState implements OperatorState {

    protected final SqlQueryBuilder queryBuilder;
    protected final List<Object> values;

    public BaseState(SqlQueryBuilder queryBuilder) {
        this(queryBuilder, new LinkedList<>());
    }

    public BaseState(SqlQueryBuilder queryBuilder, List<Object> values) {
        this.queryBuilder = queryBuilder;
        this.values = values;
    }

    @Override
    public void withOperator(OperatorType operator) {
        switch (operator) {
            case EQUAL:
                queryBuilder.changeState(new EqualState(queryBuilder));
                break;
            case LESS:
                queryBuilder.changeState(new LessState(queryBuilder));
                break;
            case GREATER:
                queryBuilder.changeState(new GreaterState(queryBuilder));
                break;
            case BETWEEN:
                queryBuilder.changeState(new BetweenState(queryBuilder));
                break;
            case LIKE:
                queryBuilder.changeState(new LikeState(queryBuilder));
                break;
            case NOT:
                queryBuilder.toggleNot();
                queryBuilder.changeState(new EqualState(queryBuilder));
                break;
            default:
                throw new InvalidQueryException("The operator does not exist...");
        }
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

    @Override
    public void update() {
        Condition newCondition = apply();
        queryBuilder.applyCondition(newCondition);
        values.clear();
    }
}
