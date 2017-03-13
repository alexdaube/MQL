package domain.query.builder;

import com.healthmarketscience.sqlbuilder.BetweenCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class BetweenState extends BaseState {

    private boolean valueApplied;

    public BetweenState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
        valueApplied = false;
    }

    @Override
    public void withOperator(OperatorType operator) {
        validate();
        super.withOperator(operator);
    }

    @Override
    public void and() {
        if (values.size() >= 2 || valueApplied) {
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
        valueApplied = true;
        return new BetweenCondition(queryBuilder.getAttribute(), values.get(0), values.get(1));
    }

    private void validate() {
        if (values.size() < 2 && !valueApplied) {
            throw new InvalidQueryException("Between statement needs two values");
        }
    }
}
