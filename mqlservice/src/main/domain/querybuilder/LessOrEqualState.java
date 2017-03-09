package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class LessOrEqualState extends BaseState {
    public LessOrEqualState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("The Less or Equal expression accept only one value...");
        }
        return BinaryCondition.lessThanOrEq(queryBuilder.getAttribute(), values.get(0));
    }
}
