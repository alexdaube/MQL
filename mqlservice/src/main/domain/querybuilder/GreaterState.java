package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class GreaterState extends BaseState {
    private boolean applied;

    public GreaterState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
        applied = false;
    }

    @Override
    public void withEquals() {
        if (!applied) {
            queryBuilder.changeState(new GreaterOrEqualState(queryBuilder));
        } else {
            super.withEquals();
        }
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("Greater than expression only accept one value...");
        }
        applied = true;
        return BinaryCondition.greaterThan(queryBuilder.getAttribute(), values.get(0));
    }
}
