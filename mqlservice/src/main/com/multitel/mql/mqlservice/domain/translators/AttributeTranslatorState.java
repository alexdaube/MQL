package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.StringQuery;
import com.multitel.mql.mqlservice.domain.interpreters.*;

import java.util.LinkedList;
import java.util.List;

public class AttributeTranslatorState implements QueryTranslatorState {
    private final QueryTranslator queryTranslator;
    private final Interpreter interpreter;

    public AttributeTranslatorState(QueryTranslator queryTranslator) {
        List<String> operators = new LinkedList<>();
        operators.add("is");
        operators.add("equal");
        operators.add("to");
        operators.add("in");
        operators.add("or");
        operators.add("less");
        operators.add("greater");
        interpreter = new OperatorInterpreter(operators);
        this.queryTranslator = queryTranslator;
    }

    @Override
    public boolean translate(StringQuery stringQuery) {
        if (interpreter.interpret(stringQuery, queryTranslator.getQueryBuilder())) {
            queryTranslator.changeState(new OperatorTranslatorState(queryTranslator));
            return false;
        }
        throw new InvalidQueryException("The attribute should be followed by an operator");
    }
}
