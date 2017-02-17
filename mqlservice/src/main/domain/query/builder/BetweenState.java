package domain.query.builder;

import com.healthmarketscience.sqlbuilder.BetweenCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class BetweenState extends BaseState {

    public BetweenState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void withEquals() {
        validate();
        super.withEquals();
    }

    @Override
    public void withGreater() {
        validate();
        super.withGreater();
    }

    @Override
    public void withLess() {
        validate();
        super.withLess();
    }

    @Override
    public void withBetween() {
        validate();
        super.withBetween();
    }

    @Override
    public void and() {
        if (values.size() >= 2) {
            super.and();
        }
    }

    @Override
    public void or() {
        validate();
        super.or();
    }

    @Override
    public Condition apply() {
        validate();
        return new BetweenCondition(queryBuilder.getAttribute(), values.get(0), values.get(1));
    }

    private void validate() {
        if (values.size() < 2) {
            throw new InvalidQueryException("Between statement needs two values");
        }
    }
}
