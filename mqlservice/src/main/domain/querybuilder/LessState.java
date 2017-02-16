package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class LessState extends BaseState {
    public LessState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void withEquals() {
        queryBuilder.changeState(new LessOrEqualState(queryBuilder));
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("A less than expression only accept one value...");
        }
        return BinaryCondition.lessThan(queryBuilder.getAttribute(), values.get(0));
    }
}
