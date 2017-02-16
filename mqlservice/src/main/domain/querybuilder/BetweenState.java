package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BetweenCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class BetweenState extends BaseState {
    private int count;

    public BetweenState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
        count = 0;
    }

    @Override
    public void and() {
        if (count++ > 0 || values.size() == 2) {
            super.and();
        }
    }

    @Override
    public void or() {
        if (count < 1 || values.size() != 2) {
            throw new InvalidQueryException("Use and not or with between statement...");
        }
        super.or();
    }

    @Override
    public Condition apply() {
        if (values.size() != 2) {
            throw new InvalidQueryException("Between statement needs two values");
        }
        return new BetweenCondition(queryBuilder.getAttribute(), values.get(0), values.get(1));
    }
}
