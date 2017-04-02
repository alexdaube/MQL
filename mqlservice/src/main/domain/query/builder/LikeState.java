package domain.query.builder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import domain.InvalidQueryException;

public class LikeState extends BaseState {

    public LikeState(SqlQueryBuilder queryBuilder) {
        super(queryBuilder);
    }

    @Override
    public Condition apply() {
        if (values.size() != 1) {
            throw new InvalidQueryException("Like operator takes one argument...");
        }
        return BinaryCondition.like(queryBuilder.getAttribute(), "%" + values.get(0) + "%");
    }
}
