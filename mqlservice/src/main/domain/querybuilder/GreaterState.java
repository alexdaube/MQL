package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import domain.InvalidQueryException;

public class GreaterState extends BaseState {
    public GreaterState(QueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void withEquals() {
        queryBuilder.changeState(new GreaterOrEqualState(queryBuilder));
    }

    @Override
    public void apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("Greater than expression only accept one value...");
        }
        queryBuilder.newCondition = BinaryCondition.greaterThan(queryBuilder.getAttribute(), values.get(0));
    }
}
