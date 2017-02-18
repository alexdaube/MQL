package domain.interpreters;

import domain.query.Query;
import domain.query.builder.QueryBuilder;

public interface Interpreter {
    boolean interpret(Query query, QueryBuilder queryBuilder);
}
