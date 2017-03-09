package domain.interpreters;

import domain.Query;
import domain.querybuilder.QueryBuilder;

public interface Interpreter {
    boolean interpret(Query query, QueryBuilder queryBuilder);
}
