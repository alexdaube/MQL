package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.Query;

public interface Interpreter {
    boolean interpret(Query query, QueryBuilder queryBuilder);
}
