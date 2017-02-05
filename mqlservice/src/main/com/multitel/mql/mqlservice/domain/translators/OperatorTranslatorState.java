package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.StringQuery;
import com.multitel.mql.mqlservice.domain.interpreters.Interpreter;
import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.interpreters.OperatorInterpreter;
import com.multitel.mql.mqlservice.domain.interpreters.ValueInterpreter;

import java.util.LinkedList;
import java.util.List;

public class OperatorTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter valueInterpreter;
    private final OperatorInterpreter operatorInterpreter;

    public OperatorTranslatorState(QueryTranslator queryTranslator) {
        valueInterpreter = new ValueInterpreter();
        this.queryTranslator = queryTranslator;
        List<String> operators = new LinkedList<>();
        operators.add("is");
        operators.add("equal");
        operators.add("to");
        operators.add("in");
        operators.add("or");
        operators.add("less");
        operators.add("greater");
        operatorInterpreter = new OperatorInterpreter(operators);
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (valueInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new ValueTranslatorState(queryTranslator));
            return false;
        } else if (operatorInterpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            return false;
        }
        throw new InvalidQueryException("An operator should be followed by an other operator or by a value...");
    }
}
