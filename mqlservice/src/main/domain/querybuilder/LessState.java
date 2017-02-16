package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import domain.InvalidQueryException;

public class LessState extends BaseState {
    public LessState(QueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void withEquals() {
        queryBuilder.changeState(new LessOrEqualState(queryBuilder));
    }

    @Override
    public void apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("A less than expression only accept one value...");
        }
        queryBuilder.newCondition = BinaryCondition.lessThan(queryBuilder.getAttribute(), values.get(0));
    }
}
