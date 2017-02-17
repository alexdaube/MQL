package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class LessState extends BaseState {
    private boolean applied;

    public LessState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
        applied = false;
    }

    @Override
    public void withEquals() {
        if (!applied) {
            queryBuilder.changeState(new LessOrEqualState(queryBuilder));
        } else {
            super.withEquals();
        }
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("A less than expression only accept one value...");
        }
        applied = true;
        return BinaryCondition.lessThan(queryBuilder.getAttribute(), values.get(0));
    }
}
