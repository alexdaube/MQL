package domain.interpreters;

import domain.query.Query;
import domain.query.builder.QueryBuilder;

public interface Interpreter extends Suggester {
    boolean interpret(Query query, QueryBuilder queryBuilder);
}
