package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;

import java.util.List;

public class OperatorInterpreter implements Interpreter {
    private final List<String> operators;

    public OperatorInterpreter(List<String> operators) {
        this.operators = operators;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        for (String operator: operators) {
            if (query.extractFirst(operator)) {
                queryBuilder.withOperator(operator);
                return true;
            }
        }
        return false;
    }
}
