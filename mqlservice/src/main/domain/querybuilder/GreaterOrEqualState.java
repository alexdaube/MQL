package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class GreaterOrEqualState extends BaseState {
    public GreaterOrEqualState(QueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("The Greater or Equal expression accept only one value...");
        }
        return BinaryCondition.greaterThanOrEq(queryBuilder.getAttribute(), values.get(0));
    }
}
