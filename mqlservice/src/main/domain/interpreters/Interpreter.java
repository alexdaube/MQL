package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;

public interface Interpreter {
    boolean interpret(StringQuery query, QueryBuilder queryBuilder);
}
