package domain.query.builder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.InCondition;
import domain.InvalidQueryException;

public class EqualState extends BaseState {

    public EqualState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public Condition apply() {
        if (values.size() > 1) {
            return new InCondition(queryBuilder.getAttribute(), values);
        } else if (values.size() == 1) {
            return BinaryCondition.equalTo(queryBuilder.getAttribute(), values.get(0));
        }
        throw new InvalidQueryException("The equal operator should be followed by value(s)...");
    }
}
