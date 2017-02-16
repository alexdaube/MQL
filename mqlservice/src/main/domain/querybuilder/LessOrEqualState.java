package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import domain.InvalidQueryException;

public class LessOrEqualState extends BaseState {
    public LessOrEqualState(QueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("The Less or Equal expression accept only one value...");
        }
        queryBuilder.newCondition = BinaryCondition.lessThanOrEq(queryBuilder.getAttribute(), values.get(0));
    }
}
