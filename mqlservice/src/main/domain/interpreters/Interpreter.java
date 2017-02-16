package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;

public interface Interpreter {
    boolean interpret(StringQuery query, QueryBuilder queryBuilder);
}
