package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.InCondition;
import domain.InvalidQueryException;

public class EqualState extends BaseState {

    public EqualState(QueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public void apply() {
        if (values.size() > 1) {
            queryBuilder.newCondition = new InCondition(queryBuilder.getAttribute(), values);
        } else if (values.size() == 1) {
            queryBuilder.newCondition = BinaryCondition.equalTo(queryBuilder.getAttribute(), values.get(0));
        } else {
            throw new InvalidQueryException("The equal operator should be followed by value(s)...");
        }
    }
}
